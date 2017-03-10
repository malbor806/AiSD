#include <stdio.h>
#include <stdlib.h>
#include <time.h>

typedef struct node_type{
  struct node_type *prev;
  struct node_type *next;
  int value;
}node;

typedef struct list_type{
  struct node_type *start;
  struct node_type *end;
  int counter;
}list;

list *createNewList(){
  list *newList = malloc(sizeof(list));
  newList->start = NULL;
  newList->end = NULL;
  newList->counter = 0;
}

node *createNewNode(int value){
  node *newNode = malloc(sizeof(node));
  newNode->prev = NULL;
  newNode->next = NULL;
  newNode->value = value;
}

int getSize(list *list){
  return list->counter;
}

int isEmpty(list *list){
  return (list->counter == 0);
}

void printList(list *list){
  if(isEmpty(list)){
    return;
  }
  else{
    node *tmp = list->start;
    int i=1, size = getSize(list);
    while(i <= size){
    //  printf("wart  %d, prev %p adres %p, next %p \n", tmp->value, tmp->prev, tmp, tmp->next);
      printf("%d, ", tmp->value);
      tmp = tmp->next;
      i++;
    }
    printf("\n");
  }
}

void addToListValueOnIndex(list *list, int value, int index){
  node *newNode = createNewNode(value);
  if(isEmpty(list)){
    list->start = newNode;
    newNode->prev = list->start;
    newNode->next = list->start;
    list->end = list->start;
    list->counter++;
  }
  else if(index < 1){
    printf("Wrong index: %d\n",index);
  }
  else if (index == 1){
    newNode->prev = list->start->prev;
    newNode->next = list->start;
    list->start->prev = newNode;
    list->start = newNode;
    list->end->next = newNode;
    list->counter++;
  }
  else if(index > getSize(list)){
    node *tmp = list->start;
    while(tmp != list->end)
      tmp = tmp->next;
    tmp->next = newNode;
    newNode->prev = tmp;
    newNode->next = list->start;
    list->end = newNode;
    list->start->prev = newNode;
    list->counter++;
  }
  else{
    node *tmp = list->start;
    if(index < getSize(list)/2){
      int i = 1;
      while(i<index){
        tmp = tmp->next;
        i++;
      }
      newNode->next = tmp;
      newNode->prev = tmp->prev;
      tmp->prev->next = newNode;
      tmp->prev = newNode;
      list->counter++;
    }
    else{
      int c = list->counter;
  		while(c >= index){
  		    tmp=tmp->prev;
  		  c--;
  	  }
      printf("%d\n", tmp->value );
      newNode->next = tmp;
      newNode->prev = tmp->prev;
      tmp->prev->next = newNode;
      tmp->prev = newNode;
      list->counter++;
    }
  }
}

void deleteElementOnIndex(list *list, int index){
  if(index < 1 || isEmpty(list) || index > getSize(list)){
    printf("No element of this index: %d\n",index);
    return;
  }
  if (getSize(list) == 1){
    list->start = NULL;
    list->end = NULL;
    list->counter--;
  }
  else if (index == 1){
    node *tmp = list->start;
    tmp->prev->next = tmp->next;
    list->start = tmp->next;
    list->end = tmp->prev;
    tmp->next->prev = tmp->prev;
    tmp->next = NULL;
    tmp->prev = NULL;
    free(tmp);
    list->counter--;
  }
  else{
    node *tmp = list->start;
    if(index < (getSize(list)/2)){
      int i = 1;
      while(i<index){
        i++;
        tmp = tmp->next;
      }
      tmp->prev->next = tmp->next;
      tmp->next->prev = tmp->prev;
      tmp->next = NULL;
      tmp->prev = NULL;
      list->counter--;
      free(tmp);
    }
    else{
      int c = list->counter;
      while(c >= index){
          tmp=tmp->prev;
        c--;
      }
      tmp->prev->next = tmp->next;
      tmp->next->prev = tmp->prev;
      tmp->next = NULL;
      tmp->prev = NULL;
      if(index == getSize(list)){
        list->end = list->start->prev;
      }
      list->counter--;
      free(tmp);
    }
  }
}

char *searchByIndex(list *list, int index){
  if(isEmpty(list) || index > getSize(list) || index < 1){
    return "false";
  }
  node *tmp = list->start;
  int c;
  if(index < (getSize(list)/2)){
    c = 1;
  	while(c < index){
  	   tmp = tmp->next;
  		 c++;
  	}
  }
	else{
		c = list->counter;
		while(c >= index){
		    tmp=tmp->prev;
		  c--;
	  }
  }
  return "true";
}

void searchElementByIndex(list *list, int index){
  printf("search on index %d : %s \n",index, searchByIndex(list, index));
}

void addRandomElementsToList(list *list, int numberOfElements){
  int i;
  for(i = 1; i <= numberOfElements; i++){
    addToListValueOnIndex(list, rand()%100, i);
  }
}

void checkTime(list *list, int index){
  clock_t startTime, stopTime;
  printf("index: %d\n",index);
  startTime = clock();
  searchByIndex(list,index);
  stopTime = clock();
  printf("time:  %lf\n", ((stopTime-startTime)/(double)CLOCKS_PER_SEC));
}


list *merge(list *l1, list *l2){
  node *tmp = l1->start;
  l1->start->prev = l2->end;
  l1->end->next = l2->start;
  l2->start->prev = l1->end;
  l2->end->next = l1->start;
  l1->end = l2->end;
  l1->counter = l1->counter + l2->counter;
}

int main(){
  int t = time(NULL);
  srand(t);
  int n = 1000;
  list *l = createNewList();
  addRandomElementsToList(l,n);
  checkTime(l,1);
  checkTime(l,1);
  checkTime(l,1000);
  checkTime(l,500);
  list *l2 = createNewList();
  addRandomElementsToList(l2,n);
  l = merge(l,l2);
  checkTime(l,1);
  checkTime(l,2000);
  checkTime(l,1000);
}
