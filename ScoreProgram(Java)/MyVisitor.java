package dw.project;

import org.eclipse.jdt.core.dom.*;
import java.util.ArrayList;
import java.util.List;

public class MyVisitor extends ASTVisitor {
	private int count=0;

	private List<ImportDeclaration> importDeclarations;
	private List<TypeDeclaration> typeDeclarations;
    private List<FieldDeclaration> fieldDeclarations;
    private List<MethodDeclaration> methodDeclarations;
    private List<VariableDeclarationStatement> variableDeclarations;
    private List<SingleVariableDeclaration> singleVariableDeclaration;
    private List<ExpressionStatement> expressionStatement;
    
    private List<ASTNode> allNodes;
    
    public MyVisitor() {
        methodDeclarations = new ArrayList<>();
        variableDeclarations = new ArrayList<>();
        typeDeclarations = new ArrayList<>();
        importDeclarations = new ArrayList<>();
        fieldDeclarations = new ArrayList<>();
        singleVariableDeclaration = new ArrayList<>();
        expressionStatement = new ArrayList<>();

        allNodes = new ArrayList<>();
        
    }

    public List<MethodDeclaration> getMethodDeclarations() {
        return methodDeclarations;
    }

    public List<VariableDeclarationStatement> getVariableDeclarations() {
        return variableDeclarations;
    }

    public List<TypeDeclaration> getTypeDeclarations() {
        return typeDeclarations;
    }

    public List<ImportDeclaration> getImportDeclarations() {
        return importDeclarations;
    }

    public List<FieldDeclaration> getFieldDeclarations() {
        return fieldDeclarations;
    }
    
    public List<SingleVariableDeclaration> getSingleVariableDeclaration() {
        return singleVariableDeclaration;
    }
    
    public List<ExpressionStatement> getExpressionStatement() {
        return expressionStatement;
    }
    
    @Override
    public boolean visit(MethodDeclaration node) {
        methodDeclarations.add(node);
        count++;
        allNodes.add(node);
        return super.visit(node);
    }

    @Override
    public boolean visit(VariableDeclarationStatement node) {
        variableDeclarations.add(node);
        count++;
        allNodes.add(node);
        return super.visit(node);
    }

    @Override
    public boolean visit(TypeDeclaration node) {
        typeDeclarations.add(node);
        count++;
        allNodes.add(node);
        return super.visit(node);
    }

    @Override
    public boolean visit(ImportDeclaration node) {
        importDeclarations.add(node);
        count++;
        allNodes.add(node);
        return super.visit(node);
    }

    @Override
    public boolean visit(FieldDeclaration node) {
        fieldDeclarations.add(node);
        count++;
        allNodes.add(node);
        return super.visit(node);
    }
    
    @Override
    public boolean visit(SingleVariableDeclaration node) {
    	singleVariableDeclaration.add(node);
        count++;
        allNodes.add(node);
        return super.visit(node);
    }
    
    @Override
    public boolean visit(ExpressionStatement node) {
    	expressionStatement.add(node);
        count++;
        allNodes.add(node);
        return super.visit(node);
    }
    
    public int getTotalCount() {
    	return count;
    }
    
    public List<ASTNode> getAllNodes() {
        return allNodes;
    }
    
}
