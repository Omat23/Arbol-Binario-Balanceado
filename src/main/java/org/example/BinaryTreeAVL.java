package org.example;

import javax.swing.*;

public class BinaryTreeAVL {

    private NodeBinaryTreeAVL rootNodeBinaryTreeAVL;

    public BinaryTreeAVL() {
        this.rootNodeBinaryTreeAVL = null;
    }

    public void addElementsWithinBinaryTreeAVL(){
        int elementToSave = Integer.parseInt(JOptionPane.showInputDialog("Enter the element to save"));
        NodeBinaryTreeAVL newNodeBinaryTreeAVL = new NodeBinaryTreeAVL(elementToSave);
        if(this.rootNodeBinaryTreeAVL == null){
            this.rootNodeBinaryTreeAVL = newNodeBinaryTreeAVL;
        }else{
            this.rootNodeBinaryTreeAVL = addElementsWithinBinaryTreeAVL(newNodeBinaryTreeAVL, this.rootNodeBinaryTreeAVL);
        }
    }

    public NodeBinaryTreeAVL addElementsWithinBinaryTreeAVL(NodeBinaryTreeAVL newNodeBinaryTreeAVL, NodeBinaryTreeAVL auxNode){
        NodeBinaryTreeAVL fatherNodeBinaryTreeAVL = auxNode;
        //Primer caso: Mi nuevo nodo es MENOR a mi nodo actual (auxNode) por lo cual, se agrega a la izquierda
        if(newNodeBinaryTreeAVL.getDataBinaryTreeAVL() < auxNode.getDataBinaryTreeAVL()){
            //Caso 1.1: Mi nodo actual no tiene hijos izquierdos, por lo tanto, se agrega a la izquierda directamente
            if(auxNode.getSonLeftNodeBinaryTreeAVL() == null){
                auxNode.setSonLeftNodeBinaryTreeAVL(newNodeBinaryTreeAVL);
            }else{
            /*Caso 1.2: Al tener mi nodo actual una cantidad superior a un hijo a la izquierda, vamos a
            * recorrer nuestro arbol y a modificar el hijo izquierdo de nuestro nodo actual para que se forme
            * toda la rama completa. Posteriormente, vamos a esperar a que no tengamos mas hijos a la izquierda
            * para insertar, si despues de la insercion se encuentra desbalanceado, vamos a ocupar
            * rotaciones hacia la derecha, dependiendo del caso
            */
                auxNode.setSonLeftNodeBinaryTreeAVL(addElementsWithinBinaryTreeAVL(newNodeBinaryTreeAVL, auxNode.getSonLeftNodeBinaryTreeAVL()));
                //Verificamos si despues de acomodar la rama izquierda de nuestra raiz, mi elemento a agregar es menor al hijo izquierdo de mi raiz
                if(newNodeBinaryTreeAVL.getDataBinaryTreeAVL() < auxNode.getSonLeftNodeBinaryTreeAVL().getDataBinaryTreeAVL()){
                    //Verificamos si mi arbol esta desbalanceado o no
                    if((getBalanceNode(auxNode.getSonLeftNodeBinaryTreeAVL()) - getBalanceNode(auxNode.getSonRightNodeBinaryTreeAVL())) == 2){
                        fatherNodeBinaryTreeAVL = rotationRight(auxNode);
                    }else{
                        fatherNodeBinaryTreeAVL = rotationDoubleRight(auxNode);
                    }
                }
            }
        //Segundo caso: Mi nuevo nodo es MAYOR a mi nodo actual (auxNode) por lo cual, se agrega a la derecha
        }else if(newNodeBinaryTreeAVL.getDataBinaryTreeAVL() > auxNode.getDataBinaryTreeAVL()){
            //Caso 2.1: Mi nodo actual no tiene hijos derechos, por lo tanto, se agrega a la derecha directamente
            if(auxNode.getSonRightNodeBinaryTreeAVL() == null){
                auxNode.setSonRightNodeBinaryTreeAVL(newNodeBinaryTreeAVL);
            }else{
                /*Caso 2.1: Al tener mi nodo actual una cantidad superior a un hijo a la derecha, vamos a
                 * recorrer nuestro arbol y a modificar el hijo derecho de nuestro nodo actual para que se forme
                 * toda la rama completa. Posteriormente, vamos a esperar a que no tengamos mas hijos a la derecha
                 * para insertar, si despues de la insercion se encuentra desbalanceado, vamos a ocupar
                 * rotaciones hacia la izquierda, dependiendo del caso
                 */
                auxNode.setSonRightNodeBinaryTreeAVL(addElementsWithinBinaryTreeAVL(newNodeBinaryTreeAVL, auxNode.getSonRightNodeBinaryTreeAVL()));
                //Verificamos si despues de acomodar la rama izquierda de nuestra raiz, mi elemento a agregar es mayor al hijo derecho de mi raiz
                if(newNodeBinaryTreeAVL.getDataBinaryTreeAVL() > auxNode.getSonRightNodeBinaryTreeAVL().getDataBinaryTreeAVL()){
                    //Verificamos si mi arbol esta desbalanceado o no
                    if((getBalanceNode(auxNode.getSonRightNodeBinaryTreeAVL()) - getBalanceNode(auxNode.getSonLeftNodeBinaryTreeAVL())) == 2){
                        fatherNodeBinaryTreeAVL = rotationLeft(auxNode);
                    }else{
                        fatherNodeBinaryTreeAVL = rotationDoubleLeft(auxNode);
                    }
                }
            }
        //Tercer caso: Mi nodo ya existe, por lo tanto, es duplicado
        }else{
            JOptionPane.showMessageDialog(null, "Node Duplicate");
        }

        //Actualizamos la altura del nodo raiz
        if(auxNode.getSonLeftNodeBinaryTreeAVL() != null && auxNode.getSonRightNodeBinaryTreeAVL() == null){
            auxNode.setFrequencyBalanceNodeBinaryTreeAVL(auxNode.getSonLeftNodeBinaryTreeAVL()
                                                                .getFrequencyBalanceNodeBinaryTreeAVL() + 1);
        }else if(auxNode.getSonLeftNodeBinaryTreeAVL() == null && auxNode.getSonRightNodeBinaryTreeAVL() != null){
            auxNode.setFrequencyBalanceNodeBinaryTreeAVL(auxNode.getSonRightNodeBinaryTreeAVL()
                                                                .getFrequencyBalanceNodeBinaryTreeAVL() + 1);
        }else{
            auxNode.setFrequencyBalanceNodeBinaryTreeAVL(Math.max(getBalanceNode(auxNode.getSonLeftNodeBinaryTreeAVL()),
                                                                  getBalanceNode(auxNode.getSonRightNodeBinaryTreeAVL())) + 1);
        }
        return fatherNodeBinaryTreeAVL;
    }

    public int getBalanceNode(NodeBinaryTreeAVL auxNode){
        return (auxNode == null) ? -1 : auxNode.getFrequencyBalanceNodeBinaryTreeAVL();
    }

    public NodeBinaryTreeAVL rotationRight(NodeBinaryTreeAVL auxNode){
        NodeBinaryTreeAVL fatherNode = auxNode.getSonLeftNodeBinaryTreeAVL();
        auxNode.setSonLeftNodeBinaryTreeAVL(fatherNode.getSonRightNodeBinaryTreeAVL());
        fatherNode.setSonRightNodeBinaryTreeAVL(auxNode);
        //Actualizamos la frecuencia de equilibrio de ambos nodos
        auxNode.setFrequencyBalanceNodeBinaryTreeAVL(Math.max(getBalanceNode(auxNode.getSonLeftNodeBinaryTreeAVL()),
                                                              getBalanceNode(auxNode.getSonRightNodeBinaryTreeAVL())) + 1);
        fatherNode.setFrequencyBalanceNodeBinaryTreeAVL(Math.max(getBalanceNode(fatherNode.getSonLeftNodeBinaryTreeAVL()),
                                                                 getBalanceNode(fatherNode.getSonRightNodeBinaryTreeAVL())) + 1);
        return fatherNode;
    }

    public NodeBinaryTreeAVL rotationLeft(NodeBinaryTreeAVL auxNode){
        NodeBinaryTreeAVL fatherNode = auxNode.getSonRightNodeBinaryTreeAVL();
        auxNode.setSonRightNodeBinaryTreeAVL(fatherNode.getSonLeftNodeBinaryTreeAVL());
        fatherNode.setSonLeftNodeBinaryTreeAVL(auxNode);
        //Actualizamos la frecuencia de equilibrio de ambos nodos
        auxNode.setFrequencyBalanceNodeBinaryTreeAVL(Math.max(getBalanceNode(auxNode.getSonLeftNodeBinaryTreeAVL()),
                                                              getBalanceNode(auxNode.getSonRightNodeBinaryTreeAVL())) + 1);
        fatherNode.setFrequencyBalanceNodeBinaryTreeAVL(Math.max(getBalanceNode(fatherNode.getSonLeftNodeBinaryTreeAVL()),
                                                                 getBalanceNode(fatherNode.getSonRightNodeBinaryTreeAVL())) + 1);
        return fatherNode;
    }

    public NodeBinaryTreeAVL rotationDoubleRight(NodeBinaryTreeAVL auxNode){
        NodeBinaryTreeAVL fatherNode;
        auxNode.setSonLeftNodeBinaryTreeAVL(rotationRight(auxNode.getSonLeftNodeBinaryTreeAVL()));
        fatherNode = rotationLeft(auxNode);
        return fatherNode;
    }

    public NodeBinaryTreeAVL rotationDoubleLeft(NodeBinaryTreeAVL auxNode){
        NodeBinaryTreeAVL fatherNode;
        auxNode.setSonRightNodeBinaryTreeAVL(rotationLeft(auxNode.getSonRightNodeBinaryTreeAVL()));
        fatherNode = rotationRight(auxNode);
        return fatherNode;
    }

    public void showTreeInOrder(NodeBinaryTreeAVL auxNode){
        if(auxNode != null){
            showTreeInOrder(auxNode.getSonLeftNodeBinaryTreeAVL());
            System.out.print(auxNode.getDataBinaryTreeAVL() + ", ");
            showTreeInOrder(auxNode.getSonRightNodeBinaryTreeAVL());
        }
    }

    public void showTreePreOrder(NodeBinaryTreeAVL auxNode){
        if(auxNode != null){
            System.out.print(auxNode.getDataBinaryTreeAVL() + ", ");
            showTreePreOrder(auxNode.getSonLeftNodeBinaryTreeAVL());
            showTreePreOrder(auxNode.getSonRightNodeBinaryTreeAVL());
        }
    }

    public void showTreePostOrder(NodeBinaryTreeAVL auxNode){
        if(auxNode != null){
            showTreePostOrder(auxNode.getSonLeftNodeBinaryTreeAVL());
            showTreePostOrder(auxNode.getSonRightNodeBinaryTreeAVL());
            System.out.print(auxNode.getDataBinaryTreeAVL() + ", ");
        }
    }

    public NodeBinaryTreeAVL showSpecificElement(int elementToSearch, NodeBinaryTreeAVL auxNode){
        if(auxNode == null){
            return null;
        }else{
            if(elementToSearch == auxNode.getDataBinaryTreeAVL()){
                return auxNode;
            }else if(elementToSearch < auxNode.getDataBinaryTreeAVL()){
                return showSpecificElement(elementToSearch, auxNode.getSonLeftNodeBinaryTreeAVL());
            }else{
                return showSpecificElement(elementToSearch, auxNode.getSonRightNodeBinaryTreeAVL());
            }
        }
    }

    public void deleteSpecificNode(){
        int elementToDelete = Integer.parseInt(JOptionPane.showInputDialog("Enter the element to delete"));
        this.rootNodeBinaryTreeAVL = deleteSpecificNode(elementToDelete, this.rootNodeBinaryTreeAVL);
    }

    public NodeBinaryTreeAVL deleteSpecificNode(int elementToDelete, NodeBinaryTreeAVL auxNodeBinaryTreeAVL){
        //1er Paso: Recorrer el árbol hasta encontrar el nodo a eliminar
        if(auxNodeBinaryTreeAVL != null){
            //Caso 1: Mi elemento a eliminar es menor al dato de mi auxNode, por lo tanto, se recorre a la izquierda.
            if(elementToDelete < auxNodeBinaryTreeAVL.getDataBinaryTreeAVL()){
                auxNodeBinaryTreeAVL.setSonLeftNodeBinaryTreeAVL(deleteSpecificNode(elementToDelete, auxNodeBinaryTreeAVL.getSonLeftNodeBinaryTreeAVL()));
                //Caso 2: Mi elemento a eliminar es mayor al dato de mi auxNode, por lo tanto, se recorre a la derecha.
            }else if(elementToDelete > auxNodeBinaryTreeAVL.getDataBinaryTreeAVL()){
                auxNodeBinaryTreeAVL.setSonRightNodeBinaryTreeAVL(deleteSpecificNode(elementToDelete, auxNodeBinaryTreeAVL.getSonRightNodeBinaryTreeAVL()));
            }else{
        //2do Paso: Eliminar el nodo de mi arbol.

                /*Caso contrario, si no es menor ni mayor significa que el dato de mi auxNode es igual al elemento a eliminar, por lo tanto,
                    hemos encontrado el dato.
                 */

                //1er y 2do Caso: Mi nodo a eliminar NO tiene hijos o tiene UNICAMENTE UN HIJO
                if(auxNodeBinaryTreeAVL.getSonLeftNodeBinaryTreeAVL() == null || auxNodeBinaryTreeAVL.getSonRightNodeBinaryTreeAVL() == null){
                    NodeBinaryTreeAVL temporalNode = (auxNodeBinaryTreeAVL.getSonLeftNodeBinaryTreeAVL() != null) ? auxNodeBinaryTreeAVL.getSonLeftNodeBinaryTreeAVL()
                                                        : auxNodeBinaryTreeAVL.getSonRightNodeBinaryTreeAVL();
                    //Si mi nodo NO tiene hijos
                    if(temporalNode == null) {
                        auxNodeBinaryTreeAVL = null;
                    //Caso contrario, significa que tiene un hijo
                    }else{
                        auxNodeBinaryTreeAVL = temporalNode;
                    }
                //3er Caso: Mi nodo a eliminar TENGA DOS HIJOS
                }else{
                    NodeBinaryTreeAVL temporalNode = getMiniumValueNode(auxNodeBinaryTreeAVL.getSonRightNodeBinaryTreeAVL());
                    auxNodeBinaryTreeAVL.setDataBinaryTreeAVL(temporalNode.getDataBinaryTreeAVL());
                    auxNodeBinaryTreeAVL.setSonRightNodeBinaryTreeAVL(deleteSpecificNode(temporalNode.getDataBinaryTreeAVL(), auxNodeBinaryTreeAVL.getSonRightNodeBinaryTreeAVL()));
                }
            }

            //Actualizamos la altura de nuestro auxNode.
            if(auxNodeBinaryTreeAVL != null){
                auxNodeBinaryTreeAVL.setFrequencyBalanceNodeBinaryTreeAVL(Math.max(getBalanceNode(auxNodeBinaryTreeAVL.getSonLeftNodeBinaryTreeAVL()),
                                                                            getBalanceNode(auxNodeBinaryTreeAVL.getSonRightNodeBinaryTreeAVL())) + 1);

            //Calculamos el balance de nuestro auxNode para verificar si es necesario hacer una rotacion o no
                int balanceNode = getBalanceNode(auxNodeBinaryTreeAVL.getSonRightNodeBinaryTreeAVL()) - getBalanceNode(auxNodeBinaryTreeAVL.getSonLeftNodeBinaryTreeAVL());
                return getRotations(auxNodeBinaryTreeAVL, balanceNode, auxNodeBinaryTreeAVL.getSonLeftNodeBinaryTreeAVL(), auxNodeBinaryTreeAVL.getSonRightNodeBinaryTreeAVL());
            }
            return auxNodeBinaryTreeAVL;
        }
        return null;
    }

    public NodeBinaryTreeAVL getMiniumValueNode(NodeBinaryTreeAVL auxNode){
        NodeBinaryTreeAVL boxNode = auxNode;
        while(boxNode.getSonLeftNodeBinaryTreeAVL() != null){
            boxNode = boxNode.getSonLeftNodeBinaryTreeAVL();
        }
        return boxNode;
    }

    public NodeBinaryTreeAVL getRotations(NodeBinaryTreeAVL auxNode, int balanceNode, NodeBinaryTreeAVL sonLeft, NodeBinaryTreeAVL sonRight){
        if(balanceNode == 2 && getBalanceNode(sonLeft) > getBalanceNode(sonRight)){
            return rotationRight(auxNode);
        }else if(balanceNode == 2 && getBalanceNode(sonLeft) >= getBalanceNode(sonRight)){
            return rotationDoubleRight(auxNode);
        } else if(balanceNode == 2 && getBalanceNode(sonLeft) < getBalanceNode(sonRight)){
            return rotationLeft(auxNode);
        }else{
            return rotationDoubleLeft(auxNode);
        }
    }

    public NodeBinaryTreeAVL getRootNodeBinaryTreeAVL(){
        return this.rootNodeBinaryTreeAVL;
    }
}