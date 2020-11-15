package Services;

import Models.Document;
import Models.FileMetadata;
import Models.Permission;
import org.apache.http.HttpHost;
import org.elasticsearch.ElasticsearchException;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;

import java.io.IOException;
import java.util.HashMap;

public class ElasticService {
    public static RestHighLevelClient client;
    static{
        client = new RestHighLevelClient(
                RestClient.builder(new HttpHost("localhost", 9200, "http")));
    }

    public static void delete(String fileId, String index){
        //TODO remove all documents with fileId from index
    }

    public static void indexMetadata(String fileId, FileMetadata fileMetadata, String index){
        // TODO update the metadata in all documents of fileId in Elastic
    }

    public static void indexPermissions(String fileId, Permission[] permissions, String index){
        // TODO update the permissions in all documents of fileId in Elastic
    }

    public static void indexDocument(Document document, String index) throws IOException {
        try{
            IndexRequest request = new IndexRequest(index);
            HashMap<String,Object> map = document.getHashMap();
            request.source(map);
            IndexResponse response = client.index(request, RequestOptions.DEFAULT);
        }
        catch(Exception e){
            throw e;
        }


    }

}
