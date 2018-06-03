package n26.code.challenge.service.model;


import javax.validation.constraints.NotNull;
import java.util.Date;

public class Transaction {

    @NotNull
    private Double amount;

    @NotNull
    private Date timestamp;

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }
}
