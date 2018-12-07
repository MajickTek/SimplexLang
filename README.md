# SimplexLang
A very simple scripting language in pure Java

## Syntax
The scripting language contains one main operator, which is the arrow:
~~~
=>
~~~

The arrow separates commands from their parameters:
~~~
command=>parameter
~~~

Optionally, a second "command" can be used as a parameter with a comma:
~~~
command=>op1,op2
~~~

that is it.

## List of functions
This sction contains a list of commands, their usage, and description.

 Function       | Usage           | Description                                       |
| ------------- |-----------------| --------------------------------------------------|
| print         | `print=>op1`    | prints the text on the right of the operator      |
| parse         | `parse=>>`      | debug, prints a tree of commands                  |
| setvar        | `setvar=>op1,op2`| sets variable op1 to op2                         |
| getvar        | `getvar=>op1`    | prints the variable op1                          |
| debug         | `debug=>subcmd` | various based on the subcmd. use debug=>help.     |
| NULL          |        `>`      | used as an parameter for commands that don't need any|

As of now, creating functions is not possible, as they are hard-coded symbols within the interpreter.
