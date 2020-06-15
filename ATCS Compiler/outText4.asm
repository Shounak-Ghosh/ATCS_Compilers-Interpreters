	# Auto generated MIPS code file
	# @author Shounak Ghosh
	# @version 01/10/2020 13:51:27
.text
.globl main
main: 
	# assign a new value to the variable x
	li $v0 2
	la $t0 varx
	sw $v0 ($t0)
	# assign a new value to the variable y
	# accessing global variable varx
	la $t0 varx
	lw $v0 ($t0)
	# push $v0
	subu $sp $sp 4
	sw $v0  ($sp) 
	li $v0 1
	# pop top of stack, store in $t0
	lw $t0  ($sp) 
	addu $sp $sp 4
	# perform addition
	addu $v0 $t0 $v0
	la $t0 vary
	sw $v0 ($t0)
	# assign a new value to the variable x
	# accessing global variable varx
	la $t0 varx
	lw $v0 ($t0)
	# push $v0
	subu $sp $sp 4
	sw $v0  ($sp) 
	# accessing global variable vary
	la $t0 vary
	lw $v0 ($t0)
	# pop top of stack, store in $t0
	lw $t0  ($sp) 
	addu $sp $sp 4
	# perform addition
	addu $v0 $t0 $v0
	la $t0 varx
	sw $v0 ($t0)
	# accessing global variable varx
	la $t0 varx
	lw $v0 ($t0)
	# push $v0
	subu $sp $sp 4
	sw $v0  ($sp) 
	# accessing global variable vary
	la $t0 vary
	lw $v0 ($t0)
	# pop top of stack, store in $t0
	lw $t0  ($sp) 
	addu $sp $sp 4
	# perform multiplication
	mult $t0 $v0
	mflo $v0
	move $a0 $v0
	li $v0 1
	syscall
	li $v0 4
	la $a0 newline 
	syscall
	# exit program
	li $v0 10
	syscall
.data
newline: 
	 .asciiz "\n"
