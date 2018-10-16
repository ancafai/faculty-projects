#include "Movie.h"
#include <iostream>
#include <Windows.h>
#include <shellapi.h>
#include <assert.h>
Movie::Movie() : title(""), genre(""), year(), nr_likes(), url("") {}

Movie::Movie(const std::string &title, const std::string &genre, const int &year, const int &nr_likes, const std::string &url){
	this->title = title;
	this->genre = genre;
	this->year = year;
	this->nr_likes = nr_likes;
	this->url = url;
}

bool Movie::operator==(Movie movie) {
	return (this->genre == movie.genre && this->title == movie.title);
}

void Movie::play() {
	ShellExecuteA(NULL, NULL, "chrome.exe", this->get_url().c_str(), NULL, SW_SHOWMAXIMIZED);
}

void test_movie()
{
	Movie m1("Titlu", "Gen", 2000, 1000, "Trailer");
	assert(m1.get_title() == "Titlu");
	int new_likes = 2000;
	m1.set_nr_likes(new_likes);
	assert(m1.get_nr_likes() == 2000);
}
