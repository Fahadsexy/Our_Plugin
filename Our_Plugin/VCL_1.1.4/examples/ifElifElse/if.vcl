#output "output.txt"

#vcl.if (3+3==6)
case 1
	#vcl.if (1 == 4)
	case 1.1
	#vcl.elif (3 == 4)
	case 1.2
		#vcl.if 2 == 4
		case 1.2.1
		#vcl.elif (2+2 == 4)
		case 1.2.2
		#vcl.endif 
	#vcl.else 
	case 1.3
	#vcl.endif
#vcl.elif (2 == 4)
case 2
#vcl.elif (1 == 34)
case 3
#vcl.else
case 4
#vcl.endif