main:
addi $t0, $zero, 5
addi $t1, $zero, 10
add $t2, $t0, $t1
and $t3, $t0, $t1
or $t4, $t0, $t1
andi $t5, $t2, 2
ori $t6, $t3, 8
slti $t7, $t1, 15
beq $t7, $zero, label 
addi $t1, $t1, 1
label:
addi $t0, $t0, 1
j main
