package net.engineeringdigest.journalApp.journalApp.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document("sample_data")
public class SampleData {
    @Id
    private String id;
    private String name;
    private String value;

}

