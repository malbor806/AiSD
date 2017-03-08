#include <stdio.h>
#include <stdlib.h>

typedef struct node_type{
  int value;
  struct node_type *next;
}node;

typedef struct fifo_type{
  struct node_type *start;
  struct node_type *end;
  int size;
}fifo;

fifo *createNewFifo(){
  fifo *queue = malloc(sizeof(fifo));
  queue->start = NULL;
  queue->end = NULL;
  queue->size = 0;
}

node *createNewNode(int value){
  node *newNode = malloc(sizeof(node));
  newNode->value = value;
  newNode->next=NULL;
}

int getSize(fifo *queue){
  return queue->size;
}

int isEmpty(fifo *queue){
  return (queue->size == 0 ? 1 : 0);
}

void printQueue(fifo *queue){
  long unsigned int c = 0;
  if (!isEmpty(queue)){
      node *start = queue->start;
      while(start != NULL){
        printf("%d, ", start->value);
        start = start->next;
      }
      printf("\n");
  }
}

void push(fifo *queue, int value){
  node *newNode = createNewNode(value);
  if(isEmpty(queue)){
    queue->start = queue->end = newNode;
    queue->size++;
  }else{
      queue->end->next = newNode;
      queue->end = newNode;
      queue->size++;
  }
}

int pop(fifo *queue){
  if (isEmpty(queue)){
    return -99999999;
  }
  else{
    int value = queue->start->value;
    queue->start = queue->start->next;
    queue->size--;
    return value;
  }
}

void popAll(fifo *queue){
  while(!isEmpty(queue)){
    int value = pop(queue);
    printf("%d, ", value);
  }
  printf("\n");
}

int main(){
  fifo *f = createNewFifo();
  printf("size 1: %d \n", getSize(f));
  printQueue(f);
  printf("pop %d\n", pop(f));
  push(f, 5);
  push(f,66);
  printf("size 2: %d \n", getSize(f));
  push(f,12);
  push(f,166);
  push(f,124);
  printQueue(f);
  printf("size 3: %d \n", getSize(f));
  printf("pop %d\n", pop(f));
  printf("size 4: %d \n", getSize(f));
  popAll(f);
  printf("size 5: %d \n", getSize(f));
}
