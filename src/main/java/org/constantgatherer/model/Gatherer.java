package org.constantgatherer.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * User: ggomes
 * Date: 12-01-2014
 * Time: 13:23
 */
@Entity
public class Gatherer {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @Column(unique = true)
    private String name;
    private GathererType type;
    private String url;
    private boolean global;
    @OneToMany(cascade = CascadeType.ALL)
    private List<GathererFragment> fragments = new ArrayList<GathererFragment>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isGlobal() {
        return global;
    }

    public void setGlobal(boolean global) {
        this.global = global;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public GathererType getType() {
        return type;
    }

    public void setType(GathererType type) {
        this.type = type;
    }

    public List<GathererFragment> getFragments() {
        return fragments;
    }

    public void setFragments(List<GathererFragment> fragments) {
        this.fragments = fragments;
    }
}
