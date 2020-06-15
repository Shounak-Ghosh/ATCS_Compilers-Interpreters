	# Auto generated MIPS code file
	# @author Shounak Ghosh
	# @version 01/10/2020 13:51:27
.text
.globl main
main: 
	# assign a new value to the variable m
	li $v0 9
	la $t0 varm
	sw $v0 ($t0)
	# assign a new value to the variable n
	li $v0 8
	la $t0 varn
	sw $v0 ($t0)
	# assign a new value to the variable k
	# push $ra
	subu $sp $sp 4
	sw $ra  ($sp) 
	li $v0 5
	# push $v0
	subu $sp $sp 4
	sw $v0  ($sp) 
	li $v0 5
	# push $v0
	subu $sp $sp 4
	sw $v0  ($sp) 
	li $v0 1
	# pop top of stack, store in $t0
	lw $t0  ($sp) 
	addu $sp $sp 4
	# perform addition
	addu $v0 $t0 $v0
	# push $v0
	subu $sp $sp 4
	sw $v0  ($sp) 
	li $v0 5
	# push $v0
	subu $sp $sp 4
	sw $v0  ($sp) 
	li $v0 2
	# pop top of stack, store in $t0
	lw $t0  ($sp) 
	addu $sp $sp 4
	# perform addition
	addu $v0 $t0 $v0
	# push $v0
	subu $sp $sp 4
	sw $v0  ($sp) 
	li $v0 0
	# push $v0
	subu $sp $sp 4
	sw $v0  ($sp) 
	jal procfoo
	# pop return value off the stack
	lw $v0  ($sp) 
	addu $sp $sp 4
	# pop parameter off the stack
	lw $v0  ($sp) 
	addu $sp $sp 4
	# pop parameter off the stack
	lw $v0  ($sp) 
	addu $sp $sp 4
	# pop parameter off the stack
	lw $v0  ($sp) 
	addu $sp $sp 4
	# pop top of stack, store in $ra
	lw $ra  ($sp) 
	addu $sp $sp 4
	la $t0 vark
	sw $v0 ($t0)
	# accessing global variable vark
	la $t0 vark
	lw $v0 ($t0)
	move $a0 $v0
	li $v0 1
	syscall
	li $v0 4
	la $a0 newline 
	syscall
	# exit program
	li $v0 10
	syscall
procfoo:
	li $v0 0
	# push local variable a to memory stack
	subu $sp $sp 4
	sw $v0 ($sp)
	li $v0 0
	# push local variable b to memory stack
	subu $sp $sp 4
	sw $v0 ($sp)
	li $v0 0
	# push local variable c to memory stack
	subu $sp $sp 4
	sw $v0 ($sp)
	# assign a new value to the variable a
	li $v0 1
	# push $v0
	subu $sp $sp 4
	sw $v0  ($sp) 
	# accessing local variable a
	lw $v0 4($sp)
	# pop top of stack, store in $t0
	lw $t0  ($sp) 
	addu $sp $sp 4
	# perform addition
	addu $v0 $t0 $v0
	sw $v0 0($sp)
	# assign a new value to the variable b
	li $v0 2
	sw $v0 4($sp)
	# assign a new value to the variable c
	li $v0 3
	sw $v0 8($sp)
	# assign a new value to the variable foo
	li $v0 4
	sw $v0 12($sp)
	# accessing local variable a
	lw $v0 0($sp)
	move $a0 $v0
	li $v0 1
	syscall
	li $v0 4
	la $a0 newline 
	syscall
	# pop local variable off the stack
	lw $v0  ($sp) 
	addu $sp $sp 4
	# pop local variable off the stack
	lw $v0  ($sp) 
	addu $sp $sp 4
	# pop local variable off the stack
	lw $v0  ($sp) 
	addu $sp $sp 4
	jr $ra
.data
newline: 
	 .asciiz "\n"
vark:
	 .word 0
varm:
	 .word 0
varn:
	 .word 0
