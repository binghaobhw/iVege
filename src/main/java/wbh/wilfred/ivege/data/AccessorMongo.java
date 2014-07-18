package wbh.wilfred.ivege.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;

public abstract class AccessorMongo {
    @Autowired
    private MongoOperations mongoTemplate;

    public abstract String collectionName();

    public void insert(Object o) {
        mongoTemplate.insert(o, collectionName());
    }

    public <T> List<T> find(Query query, Class<T> entityClass) {
        return mongoTemplate.find(query, entityClass, collectionName());
    }

    public <T> List<T> findAll(Class<T> entityClass) {
        return mongoTemplate.findAll(entityClass, collectionName());
    }
}
