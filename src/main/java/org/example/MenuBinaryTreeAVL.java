package org.example;

import javax.swing.*;

public class MenuBinaryTreeAVL {

    private BinaryTreeAVL binaryTreeAVL;

    public MenuBinaryTreeAVL() {
        this.binaryTreeAVL = new BinaryTreeAVL();
    }

    public int chooseOption(){
        return Integer.parseInt(JOptionPane.showInputDialog("""
                                                            1.- Add Elements Within Tree
                                                            2.- Show Elements In Order
                                                            3.- Show Elements Pre Order
                                                            4.- Show Elements Post Order
                                                            5.- Show Specific Elements
                                                            """));
    }

    public void executeOption(){
        int option = 0;

        while(option < 6){
            option = chooseOption();
            switch (option){
                case 1: this.binaryTreeAVL.addElementsWithinBinaryTreeAVL();
                        break;

                case 2: this.binaryTreeAVL.showTreeInOrder(this.binaryTreeAVL.getRootNodeBinaryTreeAVL());
                        System.out.println();
                        break;

                case 3: this.binaryTreeAVL.showTreePreOrder(this.binaryTreeAVL.getRootNodeBinaryTreeAVL());
                        System.out.println();
                        break;

                case 4: this.binaryTreeAVL.showTreePostOrder(this.binaryTreeAVL.getRootNodeBinaryTreeAVL());
                        System.out.println();
                        break;

                case 5: this.binaryTreeAVL.showSpecificElement(Integer.parseInt(JOptionPane.showInputDialog("Enter the element to search")),
                                                                this.binaryTreeAVL.getRootNodeBinaryTreeAVL());
                        break;

                default: JOptionPane.showMessageDialog(null, "Option Not Found");
            }
        }
    }
}
