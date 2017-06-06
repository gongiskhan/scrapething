package org.constantgatherer.model;

import javax.persistence.*;
import java.util.HashMap;
import java.util.Map;

/**
 * User: ggomes
 * Date: 13-01-2014
 * Time: 21:32
 */
@Entity
public class GatheredData {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String name;
    @ElementCollection
    @MapKeyColumn(name="key")
    @Column(name="value")
    private Map<String,String> fragments = new HashMap<String, String>();

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

    public Map<String, String> getFragments() {
        return fragments;
    }

    public void setFragments(Map<String, String> fragments) {
        this.fragments = fragments;
    }
}
