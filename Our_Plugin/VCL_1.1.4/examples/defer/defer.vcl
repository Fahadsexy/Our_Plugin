#output "output.txt"

#set b = "c"
#set c = 3

#set-defer a = ?@x?
% The evaluation will be deferred.
% The Value of x is not known yet. In variable a we store the expression,
% and when we refer to variable a the processor evaluates it.

#adapt "a.vcl"