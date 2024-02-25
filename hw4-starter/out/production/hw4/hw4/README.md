# Discussion

**Document all error conditions you determined and why they are error
 conditions. Do this by including the inputs that you used to test your
  program and what error conditions they exposed:**

Error Conditions:
Case 1:
 1.0
 ERROR: bad token
 blah
 ERROR: bad token
 !+
 ERROR: bad token
 " "
 ERROR: bad token
 !
-> all the inputs are not valid inputs

Case 2:
 1 2
 ?
 [1, 2]
 +
 ?
 [3]
 +
 ERROR: not enough operands to perform calculation!
 ?
 [3]
 -
 ERROR: not enough operands to perform calculation!
 ?
 [3]
 *
 ERROR: not enough operands to perform calculation!
 ?
 [3]
 /
 ERROR: not enough operands to perform calculation!
 ?
 [3]
 %
 ERROR: not enough operands to perform calculation!
 ?
 [3]
 !
 -> trying to perform operations when only 1 value in stack results in empty exception

Case 3:
1 0
?
[1, 0]
%
ERROR: modulo by 0
?
[1, 0]
/
ERROR: division by 0
?
[1, 0]
!
-> division and modulo by 0 produces arithmetic exception

Case 4:
?
[]
.
ERROR: no values in stack
?
[]
!
-> trying to see value in stack when there are none results in empty exception