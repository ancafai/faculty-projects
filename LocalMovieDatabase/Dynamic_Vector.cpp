/*#include "Dynamic_Vector.h"

template<class T>
Dynamic_Vector<T>::Dynamic_Vector() {
	this->size = 0;
	this->cap = 0;
	this->arr = new T[cap];
}

Dynamic_Vector<T>::Dynamic_Vector(const Dynamic_Vector &vector) {
	this->size = vector.size;
	this->cap = vector.cap;
	this->arr = new T[this->cap];
	for (int i = 0; i < this->size; i++)
		this->arr[i] = vector.arr[i];
}

Dynamic_Vector<T>::~Dynamic_Vector() {
	delete[] this->arr;
}

Dynamic_Vector<T> &Dynamic_Vector<T>::operator=(const Dynamic_Vector &vector) {
	if (this == &vector)
		return *this;
	this->size = vector.size;
	this->cap = vector.cap;

	delete[] this->arr;
	this->arr = new T[this->cap];
	for (int i = 0; i < this->size; i++) 
		this->arr[i] = vector.arr[i];
	
	return *this;
}

Dynamic_Vector<T> &Dynamic_Vector<T>::operator+(const T &elem) {
	if (this->size == this->cap)
		this->resize();
	this->arr[this->size] = elem;
	this->size++;

	return *this;
}

Dynamic_Vector<T> &Dynamic_Vector<T>::operator-(const T &elem) {
	for (int i = 0; i < this->size; i++)
	{
		if (this->arr[i] == elem)
		{
			for (int j = i + 1; j < this->size; j++) {
				this->arr[j - i] = this->arr[j];
			}
		}
	}
	
	this->size--;
	return *this;
}

void Dynamic_Vector<T>::update_vector(const T &elem, int pos) {
	this->arr[pos] = elem;
}

T& Dynamic_Vector<T>::operator[](int pos) const
{
	return this->arr[pos];
}

template<typename T>
void Dynamic_Vector<T>::resize(int factor) {
	this->cap *= factor;

	T* els = new T[this->cap];
	for (int i = 0; i < this->size; i++)
		this->arr[i] = els[i];

	delete[] this->arr;
	arr = els;
}

T* Dynamic_Vector<T>::get_all_elems() const {
	return this->arr;
}

Dynamic_Vector<T> &operator+(const T &elem, Dynamic_Vector<T> &vector) {
	if (vector.get_capacity() > vector.get_size())
		vector.resize();
	vector.get_all_elems()[vector.get_size()] = elem;
	vector.inc_size(); 

	return vector;
}

*/