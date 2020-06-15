	# Auto generated MIPS code file
	# @author Shounak Ghosh
	# @version 01/10/2020 13:51:27
.text
.globl main
main: 
	# assign a new value to the variable x
	# push $ra
	subu $sp $sp 4
	sw $ra  ($sp) 
	li $v0 2
	# push $v0
	subu $sp $sp 4
	sw $v0  ($sp) 
	li $v0 4
	# push $v0
	subu $sp $sp 4
	sw $v0  ($sp) 
	li $v0 0
	# push $v0
	subu $sp $sp 4
	sw $v0  ($sp) 
	jal proccountUp
	# pop return value off the stack
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
	la $t0 varx
	sw $v0 ($t0)
	# exit program
	li $v0 10
	syscall
proccountUp:
	# accessing local variable count
	lw $v0 8($sp)
	move $t2 $v0
	# accessing local variable max
	lw $v0 4($sp)
	move $t3 $v0
	# return true if less than equal to
	bgt $t2 $t3 endif1
	# accessing local variable count
	lw $v0 8($sp)
	move $a0 $v0
	li $v0 1
	syscall
	li $v0 4
	la $a0 newline 
	syscall
	# assign a new value to the variable ignore
	# push $ra
	subu $sp $sp 4
	sw $ra  ($sp) 
	# accessing local variable count
	lw $v0 12($sp)
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
	# accessing local variable max
	lw $v0 12($sp)
	# push $v0
	subu $sp $sp 4
	sw $v0  ($sp) 
	li $v0 0
	# push $v0
	subu $sp $sp 4
	sw $v0  ($sp) 
	jal proccountUp
	# pop return value off the stack
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
	la $t0 varignore
	sw $v0 ($t0)
endif1:
	jr $ra
.data
newline: 
	 .asciiz "\n"
varx:
	 .word 0
varignore:
	 .word 0
