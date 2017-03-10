#include <stdio.h>
#include <stdlib.h>
#include <time.h>

typedef struct node_type{
  struct node_type *next;
  int value;
}node;

typedef struct list_type{
  struct node_type *start;
  int counter;
}list;

list *createNewList(){
  list *newList = malloc(sizeof(list));
  newList->start = NULL;
  newList->counter = 0;
}

node *createNewNode(int value){
  node *newNode = malloc(sizeof(node));
  newNode->next = NULL;
  newNode->value = value;
}

int getSize(list *list){
  return (list->counter);
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
    while(tmp != NULL){
      printf("%d, ", tmp->value);
      tmp = tmp->next;
    }
    printf("\n");
  }
}

void addFirstElement(list *list, node *node){
  list->start = node;
  list->counter++;
}

void addToListValueOnIndex(list *list, int value, int index){
  if(index < 1){
    printf("Wrong index\n");
    return;
  }
  node *newNode = createNewNode(value);
  if(isEmpty(list)){
    addFirstElement(list, newNode);
  }
    else if(index == 1){
      newNode->next = list->start;
      list->start = newNode;
      list->counter++;
    }
      else if(index > getSize(list)){
        node *tmp = list->start;
        while(tmp->next)
          tmp = tmp->next;
        tmp->next = newNode;
        list->counter++;
      }
        else{
          int i = 1;
          node *elem = list->start;
          node *prevElem;
          while(i<index){
            i++;
            prevElem = elem;
            elem = elem->next;
          }
          newNode->next = elem;
          prevElem->next = newNode;
          list->counter++;
        }
}

void deleteFromTheBeginingOfTheList(list *list){
  if(isEmpty(list)){
    return;
  }
  node *elementToRemove = list->start;
  list->start = elementToRemove->next;
  list->counter--;
  free(elementToRemove);
}

void deleteElementOnIndex(list *list, int index){
  if(index < 1 || isEmpty(list) || index > getSize(list)){
    printf("No element of this index: %d\n",index);
    return;
  }
  else if( index == 1){
    deleteFromTheBeginingOfTheList(list);
  }
  else{
    node *elem = list->start;
    node *prevElem;
    int c=1;
    while(c<index){
      prevElem = elem;
      elem = elem->next;
      c++;
    }
    prevElem->next = elem->next;
    list->counter--;
    free(elem);
  }
}

void deleteElementWithValue(list *list, int value){
  if(isEmpty(list)){
    printf("Nothing to delete\n");
    return;
  }
  node *elem = list->start;
  node *prevElem;
  int c = 1;
  while(c <= getSize(list) && elem->value != value){
    prevElem = elem;
    elem = elem->next;
    c++;
  }
  if(c == 1){
    deleteFromTheBeginingOfTheList(list);
    return;
  }
  if(c > getSize(list)){
    printf("No element with this value: %d\n", value);
    return;
  }
  prevElem->next = elem->next;
  list->counter--;
  free(elem);
}

char *searchByIndex(list *list, int index){
  if(isEmpty(list) || index > getSize(list)){
    return "false";
  }
  node *elem = list->start;
  int c = 1;
  while(c < index){
    elem = elem->next;
    c++;
  }
  return "true";
}

char *searchByValue(list *list, int value){
  if(isEmpty(list)){
    return "false";
  }
  node *elem = list->start;
  int c = 1;
  while(c <= getSize(list) && elem->value != value){
    elem = elem->next;
    c++;
  }
  return (c>getSize(list)? "false" : "true");
}

void searchElementByIndex(list *list, int index){
  printf("search on index %d : %s \n",index, searchByIndex(list, index));
}

void searchElementByValue(list *list, int value){
  printf("search value %d: %s \n", value, searchByValue(list, value));
}

void addRandomElementsToList(list *list, int numberOfElements){
  int i;
  for(i = 1; i <= numberOfElements; i++){
    addToListValueOnIndex(list, rand()%100, i);
  }
}

list *merge(list *l1, list *l2){
  node *tmp = l1->start;
  while(tmp->next){
    tmp = tmp->next;
  }
  tmp->next = l2->start;
  l1->counter = l1->counter + l2->counter;
  return l1;
}

void checkTime(list *list, int index){
  clock_t startTime, stopTime;
  printf("index: %d\n",index);
  startTime = clock();
  searchByIndex(list,index);
  stopTime = clock();
  printf("time:  %lf\n", ((stopTime-startTime)/(double)CLOCKS_PER_SEC));
}

int main(){
  list *l1 = createNewList();
  int n1 = 1000;
  srand(time(0));
  addRandomElementsToList(l1,n1);
  list *l2 = createNewList();
  addRandomElementsToList(l2,n1);
  l1 = merge(l1,l2);
  checkTime(l1,l1->counter+1);
  checkTime(l1, 1);
  checkTime(l1, rand()%l1->counter);
  checkTime(l1, l1->counter);
}
