package dw.project;

import com.github.javaparser.ast.expr.SimpleName;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.jdt.core.dom.*;

public class Program {

	public static double calculateSimilarity(List<ASTNode> allNodes, List<ASTNode> allNodes2) {
		int commonNodes = getCommonNodes(allNodes, allNodes2);
		int totalNodes = getTotalNodes(allNodes, allNodes2);

		return (double) commonNodes / totalNodes;
	}

	private static int getTotalNodes(List<ASTNode> allNodes, List<ASTNode> allNodes2) {
		Set<ASTNode> uniqueNodes = new HashSet<>();

		uniqueNodes.addAll(allNodes);

		for (ASTNode node2 : allNodes2) {
			boolean nodeFound = false;

			for (ASTNode node1 : uniqueNodes) {
				if (node1.getNodeType() == node2.getNodeType() && node1.subtreeMatch(new ASTMatcher(), node2)) {
					nodeFound = true;
					break;
				}
			}

			if (!nodeFound) {
				uniqueNodes.add(node2);
			}
		}

		return uniqueNodes.size();
	}

	public static int getCommonNodes(List<ASTNode> allNodes, List<ASTNode> allNodes2) {
		List<ASTNode> commonNodes = new ArrayList<>();

		List<ASTNode> smallerArray = allNodes.size() <= allNodes2.size() ? allNodes : allNodes2;
		List<ASTNode> largerArray = allNodes.size() > allNodes2.size() ? allNodes : allNodes2;

		for (ASTNode node1 : largerArray) {
			for (ASTNode node2 : smallerArray) {
				if (node1 == null && node2 == null) {
					commonNodes.add(node1);
					break;
				} else if (node1 != null && node2 != null) {
					if (node1.getNodeType() == node2.getNodeType() && node1.subtreeMatch(new ASTMatcher(), node2)) {
						commonNodes.add(node1);
						break;
					}
				}
			}
		}

		return commonNodes.size();
	}

	public static void main(String[] args) throws IOException {
		if (args.length == 0) {
			System.out.println("Path of java file:");
			return;
		}

		String caminhoExplorerBefore = args[0];
		String caminhoExplorerAfter = caminhoExplorerBefore.replace("before", "after");

		Path filePath = Path.of(caminhoExplorerBefore);
		Path filePath2 = Path.of(caminhoExplorerAfter);
		String codeString = Files.readString(filePath);
		String codeString2 = Files.readString(filePath2);

		ASTParser parserCode1 = ASTParser.newParser(AST.JLS15);
		parserCode1.setSource(codeString.toCharArray());
		parserCode1.setKind(ASTParser.K_COMPILATION_UNIT);

		ASTParser parserCode2 = ASTParser.newParser(AST.JLS15);
		parserCode2.setSource(codeString2.toCharArray());
		parserCode2.setKind(ASTParser.K_COMPILATION_UNIT);

		CompilationUnit cu = (CompilationUnit) parserCode1.createAST(null);
		CompilationUnit cu2 = (CompilationUnit) parserCode2.createAST(null);

		MyVisitor visitor = new MyVisitor();
		cu.accept(visitor);

		MyVisitor visitor2 = new MyVisitor();
		cu2.accept(visitor2);

		List<ASTNode> allNodes = visitor.getAllNodes();
		List<ASTNode> allNodes2 = visitor2.getAllNodes();

		double score = calculateSimilarity(allNodes, allNodes2);
		System.out.println(score);

	}

}
