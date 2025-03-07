# Cw Task Manager - User Guide

Welcome to the **Cw Task Manager!** This chatbot helps you **manage your tasks** efficiently using **simple commands**.

## 1. Features & Commands

The chatbot supports **task management commands** such as **adding, deleting, marking, finding, and filtering tasks.**  
You can interact with it through **simple commands**.

### 1.1 Task Management

| **Command**      | **Format**                                                               | **Example**                                                       |
|------------------|--------------------------------------------------------------------------|-------------------------------------------------------------------|
| **Add To-Do**    | `todo <task description>`                                                | `todo Buy groceries`                                              |
| **Add Deadline** | `deadline <task description> /by <dd/MM/yyyy HHmm>`                      | `deadline Finish report /by 15/10/2025 1430`                      |
| **Add Event**    | `event <task description> /from <dd/MM/yyyy HHmm> /to <dd/MM/yyyy HHmm>` | `event Project meeting /from 20/12/2025 1000 /to 20/12/2025 1200` |
| **List Tasks**   | `list`                                                                   | `list`                                                            |
| **Delete Task**  | `delete <task index>`                                                    | `delete 2`                                                        |
| **Mark Task**    | `mark <task index>`                                                      | `mark 1`                                                          |
| **Unmark Task**  | `unmark <task index>`                                                    | `unmark 1`                                                        |
| **Exit program** | `bye`                                                                    | `bye`                                                             |

---

### 1.2 Searching & Filtering

| **Command**      | **Format**                                    | **Example**                                         |
|------------------|---------------------------------------------|-----------------------------------------------------|
| **Find Tasks**   | `find <keywords>`                           | `find groceries`                                   |
| **Tasks Before Date** | `before <dd/MM/yyyy>`                   | `before 15/10/2025`                               |
| **Tasks On Date** | `on <dd/MM/yyyy>`                         | `on 15/10/2025`                                   |
| **Tasks After Date** | `after <dd/MM/yyyy>`                   | `after 15/10/2025`                                |

---

### 1.3 Saving & Loading Tasks
- Tasks are **automatically saved** in a file (`storage.txt`) upon **adding, marking, unmarking, or deleting tasks**.
- When the chatbot starts, it **loads tasks from the saved file** automatically.

---

## 2. Example Usage
```
Welcome to the Galaxy of Cw!
How may I help you?
----------------------------------------------------------------------------
list
----------------------------------------------------------------------------
No tasks yet! Start adding some tasks
----------------------------------------------------------------------------
deadline return book /by 15/10/2025 1700
----------------------------------------------------------------------------
Got it, I have added this task:
	[D][ ] return book (by: 15 Oct 2025, 1700)
Now you have 1 tasks.
----------------------------------------------------------------------------
event meeting /from 15/10/2025 1400 /to 15/10/2025 1600
----------------------------------------------------------------------------
Got it, I have added this task:
	[E][ ] meeting (from: 15 Oct 2025, 1400 to: 15 Oct 2025, 1600)
Now you have 2 tasks.
----------------------------------------------------------------------------
todo borrow book
----------------------------------------------------------------------------
Got it, I have added this task:
	[T][ ] borrow book
Now you have 3 tasks.
----------------------------------------------------------------------------
event project meeting /from 15/10/2025 1400 /to 15/10/2025 1600
----------------------------------------------------------------------------
Got it, I have added this task:
	[E][ ] project meeting (from: 15 Oct 2025, 1400 to: 15 Oct 2025, 1600)
Now you have 4 tasks.
----------------------------------------------------------------------------
deadline return book /by 16/10/2025 1430
----------------------------------------------------------------------------
Got it, I have added this task:
	[D][ ] return book (by: 16 Oct 2025, 1430)
Now you have 5 tasks.
----------------------------------------------------------------------------
deadline study math /by 17/10/2025 1430
----------------------------------------------------------------------------
Got it, I have added this task:
	[D][ ] study math (by: 17 Oct 2025, 1430)
Now you have 6 tasks.
----------------------------------------------------------------------------
deadline study english /by 18/10/2025 1430
----------------------------------------------------------------------------
Got it, I have added this task:
	[D][ ] study english (by: 18 Oct 2025, 1430)
Now you have 7 tasks.
----------------------------------------------------------------------------
deadline study science /by 19/10/2025 1430
----------------------------------------------------------------------------
Got it, I have added this task:
	[D][ ] study science (by: 19 Oct 2025, 1430)
Now you have 8 tasks.
----------------------------------------------------------------------------
deadline study history /by 20/10/2025 1430
----------------------------------------------------------------------------
Got it, I have added this task:
	[D][ ] study history (by: 20 Oct 2025, 1430)
Now you have 9 tasks.
----------------------------------------------------------------------------
list
----------------------------------------------------------------------------
Here are the tasks in your list:
1.[D][ ] return book (by: 15 Oct 2025, 1700)
2.[E][ ] meeting (from: 15 Oct 2025, 1400 to: 15 Oct 2025, 1600)
3.[T][ ] borrow book
4.[E][ ] project meeting (from: 15 Oct 2025, 1400 to: 15 Oct 2025, 1600)
5.[D][ ] return book (by: 16 Oct 2025, 1430)
6.[D][ ] study math (by: 17 Oct 2025, 1430)
7.[D][ ] study english (by: 18 Oct 2025, 1430)
8.[D][ ] study science (by: 19 Oct 2025, 1430)
9.[D][ ] study history (by: 20 Oct 2025, 1430)
----------------------------------------------------------------------------
on 15/10/2025
----------------------------------------------------------------------------
Tasks on 15/10/2025:
	1.[D][ ] return book (by: 15 Oct 2025, 1700)
	2.[E][ ] meeting (from: 15 Oct 2025, 1400 to: 15 Oct 2025, 1600)
	3.[E][ ] project meeting (from: 15 Oct 2025, 1400 to: 15 Oct 2025, 1600)
----------------------------------------------------------------------------
before 16/10/2025
----------------------------------------------------------------------------
Tasks before 16/10/2025:
	1.[D][ ] return book (by: 15 Oct 2025, 1700)
	2.[E][ ] meeting (from: 15 Oct 2025, 1400 to: 15 Oct 2025, 1600)
	3.[E][ ] project meeting (from: 15 Oct 2025, 1400 to: 15 Oct 2025, 1600)
----------------------------------------------------------------------------
after 16/10/2025
----------------------------------------------------------------------------
Tasks after 16/10/2025:
	1.[D][ ] study math (by: 17 Oct 2025, 1430)
	2.[D][ ] study english (by: 18 Oct 2025, 1430)
	3.[D][ ] study science (by: 19 Oct 2025, 1430)
	4.[D][ ] study history (by: 20 Oct 2025, 1430)
----------------------------------------------------------------------------
before 01/01/2000
----------------------------------------------------------------------------
No tasks found to be done before 01/01/2000
----------------------------------------------------------------------------
mark 3
----------------------------------------------------------------------------
Nice! I've marked this task as done:
	[T][X] borrow book
----------------------------------------------------------------------------
list
----------------------------------------------------------------------------
Here are the tasks in your list:
1.[D][ ] return book (by: 15 Oct 2025, 1700)
2.[E][ ] meeting (from: 15 Oct 2025, 1400 to: 15 Oct 2025, 1600)
3.[T][X] borrow book
4.[E][ ] project meeting (from: 15 Oct 2025, 1400 to: 15 Oct 2025, 1600)
5.[D][ ] return book (by: 16 Oct 2025, 1430)
6.[D][ ] study math (by: 17 Oct 2025, 1430)
7.[D][ ] study english (by: 18 Oct 2025, 1430)
8.[D][ ] study science (by: 19 Oct 2025, 1430)
9.[D][ ] study history (by: 20 Oct 2025, 1430)
----------------------------------------------------------------------------
unmark 3
----------------------------------------------------------------------------
OK, I've marked this task as not done yet:
	[T][ ] borrow book
----------------------------------------------------------------------------
list
----------------------------------------------------------------------------
Here are the tasks in your list:
1.[D][ ] return book (by: 15 Oct 2025, 1700)
2.[E][ ] meeting (from: 15 Oct 2025, 1400 to: 15 Oct 2025, 1600)
3.[T][ ] borrow book
4.[E][ ] project meeting (from: 15 Oct 2025, 1400 to: 15 Oct 2025, 1600)
5.[D][ ] return book (by: 16 Oct 2025, 1430)
6.[D][ ] study math (by: 17 Oct 2025, 1430)
7.[D][ ] study english (by: 18 Oct 2025, 1430)
8.[D][ ] study science (by: 19 Oct 2025, 1430)
9.[D][ ] study history (by: 20 Oct 2025, 1430)
----------------------------------------------------------------------------
delete 3
----------------------------------------------------------------------------
Alright, I have deleted this task!
	[T][ ] borrow book
Now you have 8 tasks.
----------------------------------------------------------------------------
list
----------------------------------------------------------------------------
Here are the tasks in your list:
1.[D][ ] return book (by: 15 Oct 2025, 1700)
2.[E][ ] meeting (from: 15 Oct 2025, 1400 to: 15 Oct 2025, 1600)
3.[E][ ] project meeting (from: 15 Oct 2025, 1400 to: 15 Oct 2025, 1600)
4.[D][ ] return book (by: 16 Oct 2025, 1430)
5.[D][ ] study math (by: 17 Oct 2025, 1430)
6.[D][ ] study english (by: 18 Oct 2025, 1430)
7.[D][ ] study science (by: 19 Oct 2025, 1430)
8.[D][ ] study history (by: 20 Oct 2025, 1430)
----------------------------------------------------------------------------
bye
----------------------------------------------------------------------------
Goodbye! See you soon!
----------------------------------------------------------------------------
```