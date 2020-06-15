	# Auto generated MIPS code file
	# @author Shounak Ghosh
	# @version 01/10/2020 13:51:27
.text
.globl main
main: 
	# assign a new value to the variable x
	li $v0 1
	la $t0 varx
	sw $v0 ($t0)
	# assign a new value to the variable ignore
	# push $ra
	subu $sp $sp 4
	sw $ra  ($sp) 
	# accessing global variable varx
	la $t0 varx
	lw $v0 ($t0)
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
	jal procprintSquare
	# pop return value off the stack
	lw $v0  ($sp) 
	addu $sp $sp 4
	# pop parameter off the stack
	lw $v0  ($sp) 
	addu $sp $sp 4
	# pop top of stack, store in $ra
	lw $ra  ($sp) 
	addu $sp $sp 4
	la $t0 varignore
	sw $v0 ($t0)
	# exit program
	li $v0 10
	syscall
procprintSquare:
	# accessing local variable n
	lw $v0 4($sp)
	# push $v0
	subu $sp $sp 4
	sw $v0  ($sp) 
	# accessing local variable n
	lw $v0 8($sp)
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
	jr $ra
.data
newline: 
	 .asciiz "\n"
varx:
	 .word 0
varignore:
	 .word 0
