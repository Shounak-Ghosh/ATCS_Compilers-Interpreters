	# Auto generated MIPS code file
	# @author Shounak Ghosh
	# @version 01/10/2020 13:51:27
.text
.globl main
main: 
	# assign a new value to the variable count
	li $v0 196
	la $t0 varcount
	sw $v0 ($t0)
	# assign a new value to the variable times
	li $v0 0
	la $t0 vartimes
	sw $v0 ($t0)
	# assign a new value to the variable ignore
	# push $ra
	subu $sp $sp 4
	sw $ra  ($sp) 
	li $v0 10
	# push $v0
	subu $sp $sp 4
	sw $v0  ($sp) 
	li $v0 13
	# push $v0
	subu $sp $sp 4
	sw $v0  ($sp) 
	li $v0 0
	# push $v0
	subu $sp $sp 4
	sw $v0  ($sp) 
	jal procprintSquares
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
	# accessing global variable varcount
	la $t0 varcount
	lw $v0 ($t0)
	move $a0 $v0
	li $v0 1
	syscall
	li $v0 4
	la $a0 newline 
	syscall
	# accessing global variable vartimes
	la $t0 vartimes
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
procprintSquares:
	li $v0 0
	# push local variable count to memory stack
	subu $sp $sp 4
	sw $v0 ($sp)
	li $v0 0
	# push local variable square to memory stack
	subu $sp $sp 4
	sw $v0 ($sp)
	# assign a new value to the variable count
	# accessing local variable low
	lw $v0 16($sp)
	sw $v0 0($sp)
while1: 
	# accessing local variable count
	lw $v0 0($sp)
	move $t2 $v0
	# accessing local variable high
	lw $v0 12($sp)
	move $t3 $v0
	# return true if less than equal to
	bgt $t2 $t3 endwhile1
	# assign a new value to the variable square
	# accessing local variable count
	lw $v0 0($sp)
	# push $v0
	subu $sp $sp 4
	sw $v0  ($sp) 
	# accessing local variable count
	lw $v0 4($sp)
	# pop top of stack, store in $t0
	lw $t0  ($sp) 
	addu $sp $sp 4
	# perform multiplication
	mult $t0 $v0
	mflo $v0
	sw $v0 4($sp)
	# accessing local variable square
	lw $v0 4($sp)
	move $a0 $v0
	li $v0 1
	syscall
	li $v0 4
	la $a0 newline 
	syscall
	# assign a new value to the variable count
	# accessing local variable count
	lw $v0 0($sp)
	# push $v0
	subu $sp $sp 4
	sw $v0  ($sp) 
	li $v0 1
	# pop top of stack, store in $t0
	lw $t0  ($sp) 
	addu $sp $sp 4
	# perform addition
	addu $v0 $t0 $v0
	sw $v0 0($sp)
	# assign a new value to the variable times
	# accessing global variable vartimes
	la $t0 vartimes
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
	la $t0 vartimes
	sw $v0 ($t0)
	j while1
endwhile1:
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
vartimes:
	 .word 0
varcount:
	 .word 0
varignore:
	 .word 0
