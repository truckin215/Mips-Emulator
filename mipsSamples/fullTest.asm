#Test ADD, ADDI, AND, ANDI, OR, ORI, SLTI, BEQ, J
main:
addi $t0, $zero, 5    # $t0 = 5
addi $t1, $zero, 10   # $t1 = 10
add $t2, $t0, $t1     # $t2 = $t0 + $t1 = 15
and $t3, $t0, $t1     # $t3 = $t0 & $t1 = 0
or $t4, $t0, $t1      # $t4 = $t0 | $t1 = 15
andi $t5, $t2, 2      # $t5 = $t2 & 2 = 2
ori $t6, $t3, 8       # $t6 = $t3 | 8 = 8
slti $t7, $t1, 15     # $t7 = $t1 < 15 = 1
beq $t7, $zero, label 
addi $t1, $t1, 1      # $t1 = $t1 + 1 = 11
label:
addi $t0, $t0, 1      # $t0 = $t0 + 1 = 6
j main                # Jump to start of main, infinite loop
