#setloop loopA
#vars
  #var a = 6
  #var var4 = 1
  #var var1
  #break varbreak
  #var var2
#endvars
#iter var1 = "1a", var2 = "2a", var4 = 3, a = 5
#iter var1 = "1b", var2 = "2b"
#iter var1 = "1c", var2 = "2c", var4 = 12
#break iterbreak
#endsetloop

#while loopA
?@loopA.a?
#endwhile