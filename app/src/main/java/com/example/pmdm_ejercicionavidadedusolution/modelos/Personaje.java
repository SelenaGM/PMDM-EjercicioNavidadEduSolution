package com.example.pmdm_ejercicionavidadedusolution.modelos;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Personaje {
	private List<String> videoGames;
	private List<String> parkAttractions;
	private List<String> films;
	private List<String> shortFilms;
	private List<String> tvShows;
	private String imageUrl;
	private String name;
	private List<String> enemies;
	@SerializedName("_id")
	private int id;
	private List<String> allies;
	private String url;

	public void setVideoGames(List<String> videoGames){
		this.videoGames = videoGames;
	}

	public List<String> getVideoGames(){
		return videoGames;
	}

	public void setParkAttractions(List<String> parkAttractions){
		this.parkAttractions = parkAttractions;
	}

	public List<String> getParkAttractions(){
		return parkAttractions;
	}

	public void setFilms(List<String> films){
		this.films = films;
	}

	public List<String> getFilms(){
		return films;
	}

	public void setShortFilms(List<String> shortFilms){
		this.shortFilms = shortFilms;
	}

	public List<String> getShortFilms(){
		return shortFilms;
	}

	public void setTvShows(List<String> tvShows){
		this.tvShows = tvShows;
	}

	public List<String> getTvShows(){
		return tvShows;
	}

	public void setImageUrl(String imageUrl){
		this.imageUrl = imageUrl;
	}

	public String getImageUrl(){
		return imageUrl;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}

	public void setEnemies(List<String> enemies){
		this.enemies = enemies;
	}

	public List<String> getEnemies(){
		return enemies;
	}

	public void setId(int id){
		this.id = id;
	}

	public int getId(){
		return id;
	}

	public void setAllies(List<String> allies){
		this.allies = allies;
	}

	public List<String> getAllies(){
		return allies;
	}

	public void setUrl(String url){
		this.url = url;
	}

	public String getUrl(){
		return url;
	}

	@Override
 	public String toString(){
		return 
			"DataItem{" + 
			"videoGames = '" + videoGames + '\'' + 
			",parkAttractions = '" + parkAttractions + '\'' + 
			",films = '" + films + '\'' + 
			",shortFilms = '" + shortFilms + '\'' + 
			",tvShows = '" + tvShows + '\'' + 
			",imageUrl = '" + imageUrl + '\'' + 
			",name = '" + name + '\'' + 
			",enemies = '" + enemies + '\'' + 
			",_id = '" + id + '\'' + 
			",allies = '" + allies + '\'' + 
			",url = '" + url + '\'' + 
			"}";
		}
}