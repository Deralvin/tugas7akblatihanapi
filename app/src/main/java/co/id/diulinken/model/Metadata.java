package co.id.diulinken.model;



import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("jsonschema2pojo")
public class Metadata {

    @SerializedName("data")
    @Expose
    private Data data;
    @SerializedName("dataset")
    @Expose
    private Dataset dataset;
    @SerializedName("organization")
    @Expose
    private Organization organization;
    @SerializedName("group")
    @Expose
    private Group group;
    @SerializedName("format")
    @Expose
    private String format;
    @SerializedName("created")
    @Expose
    private String created;
    @SerializedName("file_url")
    @Expose
    private String fileUrl;
    @SerializedName("last_modified")
    @Expose
    private String lastModified;
    @SerializedName("row_total")
    @Expose
    private Integer rowTotal;

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public Dataset getDataset() {
        return dataset;
    }

    public void setDataset(Dataset dataset) {
        this.dataset = dataset;
    }

    public Organization getOrganization() {
        return organization;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }

    public String getLastModified() {
        return lastModified;
    }

    public void setLastModified(String lastModified) {
        this.lastModified = lastModified;
    }

    public Integer getRowTotal() {
        return rowTotal;
    }

    public void setRowTotal(Integer rowTotal) {
        this.rowTotal = rowTotal;
    }

}