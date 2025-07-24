package net.engineeringdigest.journalApp.journalApp.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import net.engineeringdigest.journalApp.journalApp.enums.Sentiment;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "journaldB")
@Data
@NoArgsConstructor
public class Journals {

    @Id
    private ObjectId id;

    private String content;

    private String title;

    private Sentiment sentiment;

}
