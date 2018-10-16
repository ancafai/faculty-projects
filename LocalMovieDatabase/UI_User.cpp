#include "UI_User.h"
#include <string>
#include <iostream>

void User_UI::Movie_Menu() const {
	cout << "What would you like to do to this movie?" << endl;
	cout << "\t 1 - Play it" << endl;
	cout << "\t 2 - Add it to the watchlist" << endl;
	cout << "\t 3 - Skip it" << endl;
	cout << "\t 0 - Go back to login screen." << endl;
}

void User_UI::get_all_movies_by_genre_to_watchlist_ui() const {
	string genre;
	cout << "Please input a genre you would wish to view: ";
	getline(cin, genre);
	this->ctrl.get_genre_watchlist_to_iterate(genre);

	manage_watchlist();

}

void User_UI::manage_watchlist() const
{
	int i = 0;
	if (this->ctrl.get_genre_movies().size() == 0)
		cout << "There are no movies with that particular genre.\n";
	else
	{
		while (true)
		{
			cout << this->ctrl.get_current_movie_ctrl() << endl;

			Movie_Menu();
			int option;
			cin >> option;
			cin.clear();
			cin.ignore();
			if (option == 1)
			{
				ctrl.get_current_movie_ctrl().play();
			}
			if (option == 2)
			{
				try
				{
					ctrl.add_movie_to_watchlist(this->ctrl.get_current_movie_ctrl());
				}
				catch (char* msg)
				{
					cout << msg << endl;
					ctrl.next_movie_watchlist();
					continue;
				}
				cout << "Movie added! \n";
				ctrl.next_movie_watchlist();
			}
			if (option == 3)
			{
				this->ctrl.next_movie_watchlist();
			}
			if (option == 0)
				break;
		}
	}
}

void User_UI::delete_movie_from_user_watchlist_ui() {
	std::string title;
	cout << "Enter the movie's title you would like to remove from the watchlist: ";
	getline(cin, title);
	try
	{
		this->ctrl.delete_movie_from_watchlist(title);
	}
	catch (char *msg)
	{
		cout << msg << endl;
		return;
	}
	cout << "Movie succesfully deleted" << endl;
	cout << "Care to give it a like?(input a number)";
	int new_nr_likes;
	cin >> new_nr_likes;
	cin.ignore();
	cout << "The new number of of likes for the movie is: " << this->ctrl.rate_movie(new_nr_likes, title);
}

void User_UI::show_all_movies_from_user_watchlist_ui() const {
	std::vector <Movie> movies = this->ctrl.get_watchlist();
	if (movies.size() == 0) {
		cout << "There are no movies in the watchlist :( try adding some..." << endl;
		return;
	}

	for (auto movie : movies) {
		cout << movie << endl;
	}
}

void User_UI::saveWatchlistToFile()
{
	std::string filename;
	cout << "Input the file name (absolute path): ";
	getline(cin, filename);

	this->ctrl.saveWatchlist(filename);

	if (this->ctrl.get_watchlist().size() == 0)
	{
		cout << "Watchlist cannot be displayed!" << endl;
		return;
	}
}

void User_UI::show_user_menu() {
	cout << "Welcome to your watchlist User! What would you like to do?" << endl;
	cout << "\t 1 - Look for movies by genres" << endl;
	cout << "\t 2 - Delete a movie from your watchlist" << endl;
	cout << "\t 3 - See your watchlist!" << endl;
	cout << "\t 4 - Save watchlist!" << endl;
	cout << "\t 5 - Open file!" << endl;
	cout << "\t 0 - Go back to login screen." << endl;
}

//User run
void User_UI::run_user() {
	while (true) {
		User_UI::show_user_menu();
		int command_user;
		cout << "Input the command: ";
		cin >> command_user;
		cin.clear();
		cin.ignore();
		if (command_user == 0)
			break;

		switch (command_user) {
		case 1:
			this->get_all_movies_by_genre_to_watchlist_ui();
			break;
		case 2:
			this->delete_movie_from_user_watchlist_ui();
			break;
		case 3:
			this->show_all_movies_from_user_watchlist_ui();
			break;
		case 4:
			this->saveWatchlistToFile();
			break;
		case 5:
			this->ctrl.openWatchlist();
			break;
		default:
			cout << "Please input a valid command!" << endl;
			break;
		}
	}
}