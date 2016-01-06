package xgt.easy.webservice.request;

import xgt.easy.webservice.HttpMethod;
import xgt.easy.webservice.annotation.*;
import xgt.easy.webservice.model.Action;

@SupperAvailable
@Skip
@Filter
public class PostRequest extends SimpleRequest {
    @Path
    @Order(1)
    @Filter(Action.AFTER_ENCODE)
    private String book;
    private String name;
    private String password;
    @UrlParameter
    @Order(3)
    private String title;
    @UrlParameter
    @Rename("ss")
    @Order(2)
    private Boolean isCache;

    public Boolean getIsCache() {
        return isCache;
    }

    public void setIsCache(Boolean isCache) {
        this.isCache = isCache;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBook() {
        return book;
    }

    public void setBook(String book) {
        this.book = book;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public HttpMethod getHttpMethod() {
        return HttpMethod.POST;
    }
}
