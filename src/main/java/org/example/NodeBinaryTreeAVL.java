package org.example;

public class NodeBinaryTreeAVL {

    private int dataBinaryTreeAVL;
    private int frequencyBalanceNodeBinaryTreeAVL;
    private NodeBinaryTreeAVL sonLeftNodeBinaryTreeAVL;
    private NodeBinaryTreeAVL sonrRightNodeBinaryTreeAVL;

    public NodeBinaryTreeAVL(int dataBinaryTreeAVL) {
        this.dataBinaryTreeAVL = dataBinaryTreeAVL;
        this.frequencyBalanceNodeBinaryTreeAVL = 0;
        this.sonLeftNodeBinaryTreeAVL = null;
        this.sonrRightNodeBinaryTreeAVL = null;
    }

    public int getDataBinaryTreeAVL() {
        return dataBinaryTreeAVL;
    }

    public int getFrequencyBalanceNodeBinaryTreeAVL() {
        return frequencyBalanceNodeBinaryTreeAVL;
    }

    public void setFrequencyBalanceNodeBinaryTreeAVL(int frequencyBalanceNodeBinaryTreeAVL) {
        this.frequencyBalanceNodeBinaryTreeAVL = frequencyBalanceNodeBinaryTreeAVL;
    }

    public NodeBinaryTreeAVL getSonLeftNodeBinaryTreeAVL() {
        return sonLeftNodeBinaryTreeAVL;
    }

    public void setSonLeftNodeBinaryTreeAVL(NodeBinaryTreeAVL sonLeftNodeBinaryTreeAVL) {
        this.sonLeftNodeBinaryTreeAVL = sonLeftNodeBinaryTreeAVL;
    }

    public NodeBinaryTreeAVL getSonRightNodeBinaryTreeAVL() {
        return sonrRightNodeBinaryTreeAVL;
    }

    public void setSonRightNodeBinaryTreeAVL(NodeBinaryTreeAVL sonrRightNodeBinaryTreeAVL) {
        this.sonrRightNodeBinaryTreeAVL = sonrRightNodeBinaryTreeAVL;
    }
}
