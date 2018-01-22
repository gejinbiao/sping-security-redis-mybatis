package com.gjb.shiroTest.entity;

import com.gjb.shiroTest.shared.GuidGenerator;

import java.io.Serializable;
import java.util.Date;

/**
 * @author gejinbiao@ucfgroup.com
 * @Title
 * @Description:定义抽象的 Domain父类, 将公共属性放于些
 * @Company: ucfgroup.com
 * @Created 2018-01-15
 */
public abstract class BaseEntity implements Serializable {

    private static final long serialVersionUID = 6569365774429340632L;
    /**
     * Database id
     */
    protected int id;

    /**
     * 用于实现逻辑删除
     */
    protected boolean archived;
    /**
     * Domain business guid.
     * 业务Id
     */
    protected String guid = GuidGenerator.generate();

    /**
     * The domain create time.
     */
    protected Date createTime = new Date();

    public BaseEntity() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean archived() {
        return archived;
    }

    public BaseEntity archived(boolean archived) {
        this.archived = archived;
        return this;
    }

    public String guid() {
        return guid;
    }

    public void guid(String guid) {
        this.guid = guid;
    }

    public Date createTime() {
        return createTime;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof BaseEntity)) {
            return false;
        }
        BaseEntity that = (BaseEntity) o;
        return guid.equals(that.guid);
    }

    @Override
    public int hashCode() {
        return guid.hashCode();
    }

    //For subclass override it
    public void saveOrUpdate() {
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("{id=").append(id);
        sb.append(", archived=").append(archived);
        sb.append(", guid='").append(guid).append('\'');
        sb.append(", createTime=").append(createTime);
        sb.append('}');
        return sb.toString();
    }
}