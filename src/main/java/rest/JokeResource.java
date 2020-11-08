package rest;

import JokeDTO.ChuckDTO;
import JokeDTO.DadDTO;
import JokeDTO.OurDTO;
import com.google.gson.Gson;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeoutException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.core.MediaType;
import jokefetcher.FetchHandler;
import utils.HttpUtils;

/**
 * REST Web Service
 *
 * @author lam
 */
@Path("jokes")
public class JokeResource {

    @Context
    private UriInfo context;
    private static Gson gson = new Gson();
    private static ExecutorService es = Executors.newCachedThreadPool();
   
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getJokes() {
        return " {\"info\":\"Change me to return jokes as described in the exercise\"}";
    }
    
    @Path("combined")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getCombined() throws IOException{
        Gson gson = new Gson();
        String chuck = HttpUtils.fetchData("https://api.chucknorris.io/jokes/random");
        ChuckDTO chuckDTO = gson.fromJson(chuck, ChuckDTO.class);
             
        String dad = HttpUtils.fetchData("https://icanhazdadjoke.com");
        DadDTO dadDTO = gson.fromJson(dad, DadDTO.class);

        OurDTO ourDTO = new OurDTO(dadDTO,chuckDTO);
     
        //This is what your endpoint should return       
        String combinedJSON = gson.toJson(ourDTO);
        
        return combinedJSON;

    }
    
    @Path("parralleled")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getParralleled() throws IOException, TimeoutException, InterruptedException, ExecutionException{
            List<String> dtos = FetchHandler.runParallelWithCallables(es);
            
            ChuckDTO chuckDTO = gson.fromJson(dtos.get(0), ChuckDTO.class);
            DadDTO dadDTO = gson.fromJson(dtos.get(1), DadDTO.class);
            OurDTO combined = new OurDTO(dadDTO, chuckDTO);
            
            return gson.toJson(combined);
    }
}
