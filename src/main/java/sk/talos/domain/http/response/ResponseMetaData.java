package sk.talos.domain.http.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
public class ResponseMetaData {

    @ApiModelProperty(notes = "Number of current page.", example = "0", position = 1)
    private Integer currentPage;
    @ApiModelProperty(notes = "Last available page of items.", example = "7", position = 3)
    private Long lastPage;
    @ApiModelProperty(notes = "Count of items on single page.", example = "15", position = 4)
    private Integer perPage;
    @ApiModelProperty(notes = "API path of current page.", example = "/api/office/trips", position = 5)
    private String path;
    @ApiModelProperty(notes = "Total count of items.", example = "70", position = 7)
    private Long total;

    private ResponseMetaData() {
        // Private constructor to enforce usage of the builder
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private ResponseMetaData metaData = new ResponseMetaData();

        public Builder currentPage(Integer currentPage) {
            metaData.setCurrentPage(currentPage);
            return this;
        }

        public Builder lastPage(Long lastPage) {
            metaData.setLastPage(lastPage);
            return this;
        }

        public Builder perPage(Integer perPage) {
            metaData.setPerPage(perPage);
            return this;
        }

        public Builder path(String path) {
            metaData.setPath(path);
            return this;
        }

        public Builder total(Long total) {
            metaData.setTotal(total);
            return this;
        }

        public ResponseMetaData build() {
            return metaData;
        }
    }
}
