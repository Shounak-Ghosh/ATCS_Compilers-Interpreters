	# Auto generated MIPS code file
	# @author Shounak Ghosh
	# @version 01/10/2020 13:51:27
.text
.globl main
main: 
	li $v0 6
	# push $v0
	subu $sp $sp 4
	sw $v0  ($sp) 
	li $v0 2
	# push $v0
	subu $sp $sp 4
	sw $v0  ($sp) 
	li $v0 3
	# pop top of stack, store in $t0
	lw $t0  ($sp) 
	addu $sp $sp 4
	# perform multiplication
	mult $t0 $v0
	mflo $v0
	# pop top of stack, store in $t0
	lw $t0  ($sp) 
	addu $sp $sp 4
	# perform division
	div $t0 $v0
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
