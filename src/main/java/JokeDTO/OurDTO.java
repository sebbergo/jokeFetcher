/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JokeDTO;

/**
 *
 * @author sebas
 */
public class OurDTO {
    private String joke1;
    private String joke1ref;
    private String joke2;
    private String joke2ref;

    public OurDTO(DadDTO dadDTO, ChuckDTO chuckDTO) {
        this.joke1 = dadDTO.getJoke();
        this.joke1ref = "https://icanhazdadjoke.com";
        this.joke2 = chuckDTO.getValue();
        this.joke2ref = "https://api.chucknorris.io/jokes/random";
    }

    public String getJoke1() {
        return joke1;
    }

    public void setJoke1(String joke1) {
        this.joke1 = joke1;
    }

    public String getJoke1ref() {
        return joke1ref;
    }

    public void setJoke1ref(String joke1ref) {
        this.joke1ref = joke1ref;
    }

    public String getJoke2() {
        return joke2;
    }

    public void setJoke2(String joke2) {
        this.joke2 = joke2;
    }

    public String getJoke2ref() {
        return joke2ref;
    }

    public void setJoke2ref(String joke2ref) {
        this.joke2ref = joke2ref;
    }

    
    
}
