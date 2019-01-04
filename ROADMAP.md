Roadmap
===

This document shows planned features.

## A C/C++ implementation
Since the Java app has Java-specific features like getsystemProperties, the C version will have its own.
Probably using features from

~~~~C
#include <dlfcn.h>
~~~~

to dynamically load functions from external libraries.

Variables would be interesting. I imagine porting HashMap would do the trick.
~~~~C
#include <stdio.h>
#include <stdlib.h>
struct node{
    int key;
    int val;
    struct node *next;
};
struct table{
    int size;
    struct node **list;
};
struct table *createTable(int size){
    struct table *t = (struct table*)malloc(sizeof(struct table));
    t->size = size;
    t->list = (struct node**)malloc(sizeof(struct node*)*size);
    int i;
    for(i=0;i<size;i++)
        t->list[i] = NULL;
    return t;
}
int hashCode(struct table *t,int key){
    if(key<0)
        return -(key%t->size);
    return key%t->size;
}
void insert(struct table *t,int key,int val){
    int pos = hashCode(t,key);
    struct node *list = t->list[pos];
    struct node *newNode = (struct node*)malloc(sizeof(struct node));
    struct node *temp = list;
    while(temp){
        if(temp->key==key){
            temp->val = val;
            return;
        }
        temp = temp->next;
    }
    newNode->key = key;
    newNode->val = val;
    newNode->next = list;
    t->list[pos] = newNode;
}
int lookup(struct table *t,int key){
    int pos = hashCode(t,key);
    struct node *list = t->list[pos];
    struct node *temp = list;
    while(temp){
        if(temp->key==key){
            return temp->val;
        }
        temp = temp->next;
    }
    return -1;
}
int main(){
    struct table *t = createTable(5);
    insert(t,2,3);
    insert(t,5,4);
    printf("%d",lookup(t,5));
    return 0;
}
// from http://www.kaushikbaruah.com/posts/data-structure-in-c-hashmap/
~~~~

A String.split() could be implemented with this:
http://source-code-share.blogspot.com/2014/07/implementation-of-java-stringsplit.html?m=1

I might create a separate repo for the C version once it gets to a usable state.

## Some snippets
Generic proxy function:
~~~~cpp
#include <stdio.h>
#include <functional>

using result = std::function<std::string(std::string)>;

template<class F>
result proxy(F func) {
  //some type-traits technologies based on func type
}

//---------------------------------------------------------
double foo(double a) { /*...*/ }
auto local_foo = proxy(foo);

//use dlsym to define foo
//this snippet is from https://stackoverflow.com/questions/3194434/c-c-dynamic-loading-of-functions-with-unknown-prototype
~~~~

~~~~cpp
extern "C" int foo(char *bar) {
    return realFoo(std::string(bar);
}
//call foo() from C code.

//from https://stackoverflow.com/questions/199418/using-c-library-in-c-code
~~~~
