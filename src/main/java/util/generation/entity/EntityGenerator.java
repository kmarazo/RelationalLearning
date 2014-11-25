package util.generation.entity;

import schema.Entity;

/**
 * Created by darbour on 11/22/14.
 */
public interface EntityGenerator {
    public Entity generateEntity(String name) throws Exception;
}
