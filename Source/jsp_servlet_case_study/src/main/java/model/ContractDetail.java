package model;

public class ContractDetail {
    private int contractDetailId;
    private int quantity;

    public ContractDetail() {
    }

    public ContractDetail(int contractDetailId, int quantity) {
        this.contractDetailId = contractDetailId;
        this.quantity = quantity;
    }

    public int getContractDetailId() {
        return contractDetailId;
    }

    public void setContractDetailId(int contractDetailId) {
        this.contractDetailId = contractDetailId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
