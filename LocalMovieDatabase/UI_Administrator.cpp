#include "UI_Administrator.h"
#include "Movie_Validator.h"
#include <string>
#include <iostream>
using namespace std;

// Administrator commands

void Admin_UI::show_admin_menu() {
	cout << "Manage movie database: " << endl;
	cout << "\t 1 - Add movie to the database." << endl;
	cout << "\t 2 - Delete a movie from the database." << endl;
	cout << "\t 3 - Update a movie from the database." << endl;
	cout << "\t 4 - Display all the movies from the database." << endl;
	cout << "\t 0 - Go Back to login screen." << endl;
}

void Admin_UI::add_movie_to_admin_repository_ui() const{
	std::string title, genre, url;
	int year, nr_likes;
	cout << "Enter the movie's title you would like to add to the database: ";
	getline(cin, title);
	cout << "Enter the genre of that movie: ";
	getline(cin, genre);
	while (true)
	{
		cout << "Enter the release year: ";
		if (cin >> year)
		{
			break;
		}
		else
		{
			cout << "Please input a integer for the release year.\n";
			cin.clear();
			cin.ignore();
			continue;
		}
	}

	while (true)
	{
		cout << "Enter the number of likes: ";
		if (cin >> nr_likes)
		{
			break;
		}
		else
		{
			cout << "Please input a integer for the number of likes.\n";
			cin.clear();
			cin.ignore();
			continue;
		}
	}
	cout << "Enter the trailer link(ctrl+v): ";
	cin >> url;
	//res = 
	Movie m(title, genre, year, nr_likes, url);
	try
	{
		MovieValidator v;
		v.validate(m);
	}
	catch (ValidationException& ex)

	{
		std::cout << ex.getMessage() << std::endl;
		return;
	}
	try
	{
		this->ctrl.add_movie_to_admin_repo(title, genre, year, nr_likes, url);
	}
	catch (char *msg)
	{
		cout << msg << endl;
		return;
	}
	cout << "Movie was succesfully added.\n";
}

void Admin_UI::delete_movie_from_admin_repository_ui() const{
	std::string title, genre;
	cout << "Enter the movie's title you would like to remove from the database: ";
	getline(cin, title);
	try
	{
		this->ctrl.delete_movie_to_admin_repo(title);
	}
	catch (char *msg)
	{
		cout << msg << endl;
		return;
	}
	cout << "Movie successfully removed.\n";
}

void Admin_UI::update_movie_from_admin_repository_ui() const {
	std::string title, genre, new_url;
	int new_year, new_nr_likes;
	cout << "Enter the movie's title you would like to add to the database: ";
	getline(cin, title);
	cout << "Enter the genre of that movie: ";
	getline(cin, genre);
	cout << "Enter the year it was released: ";
	cin >> new_year;
	cin.ignore();
	cout << "Enter the number of likes of that movie (the more the merrier): ";
	cin >> new_nr_likes;
	cin.ignore();
	cout << "While you're at it, share a link to a trailer of the movie: ";
	getline(cin, new_url);
	Movie m(title, genre, new_year, new_nr_likes, new_url);
	try
	{
		MovieValidator v;
		v.validate(m);
	}
	catch (ValidationException& ex)

	{
		std::cout << ex.getMessage() << std::endl;
		return;
	}
	try
	{
		this->ctrl.update_movie_to_admin_repo(title, genre, new_year, new_nr_likes, new_url);
	}
	catch (char *msg)
	{
		cout << msg << endl;
		return;
	}
	cout << "Movie was succesfully updated.\n";
}

void Admin_UI::show_all_movies_from_admin_repository_ui() const {
	vector<Movie> movies = this->ctrl.get_admin_repo().get_admin_repo_movies();
	if (movies.size() == 0) {
		cout << "There are no movies in the repository :( try adding some..." << endl;
		return;
	}

	for (auto movie : movies) {
		cout << movie;
	}
}

void Admin_UI::run_admin() {
	while (true) {
		Admin_UI::show_admin_menu();
		int command_admin;
		cout << "Input the command: ";
		cin >> command_admin;
		cin.clear();
		cin.ignore();

			if (command_admin == 0)
				break;
			switch (command_admin) {
			case 1:
				this->add_movie_to_admin_repository_ui();
				break;
			case 2:
				this->delete_movie_from_admin_repository_ui();
				break;
			case 3:
				this->update_movie_from_admin_repository_ui();
				break;
			case 4:
				this->show_all_movies_from_admin_repository_ui();
				break;
			default:
				cout << "Please input one of the available commands" << endl;
				break;
			}
		}
}