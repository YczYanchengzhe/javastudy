# 1.文件比较符
#  -e 判断对象是否存在
#  -d 判断对象是否存在，并且为目录
#  -f 判断对象是否存在，并且为常规文件
#  -L 判断对象是否存在，并且为符号链接
#  -h 判断对象是否存在，并且为软链接
#  -s 判断对象是否存在，并且长度不为0
#  -r 判断对象是否存在，并且可读
#  -w 判断对象是否存在，并且可写
#  -x 判断对象是否存在，并且可执行
#  -O 判断对象是否存在，并且属于当前用户
#  -G 判断对象是否存在，并且属于当前用户组
#  -nt 判断file1是否比file2新  [ "/data/file1" -nt "/data/file2" ]
#  -ot 判断file1是否比file2旧  [ "/data/file1" -ot "/data/file2" ]

# 文件比较符 例子
if [ -f "/data/filename" ]; then
    echo "文件存在"
else
    echo "文件不存在"
fi

# 2.数值的比较
#  -eq	等于则为真
#  -ne	不等于则为真
#  -gt	大于则为真
#  -ge	大于等于则为真
#  -lt	小于则为真
#  -le	小于等于则为真
# 数值的比较 例子
if [ $num1 -eq $num2 ]
then
    echo '两个数相等！'
else
    echo '两个数不相等！'
fi

#  3.字符串比较
#  =	等于则为真
#  !=	不相等则为真
#  -z 字符串	字符串的长度为零则为真
#  -n 字符串	字符串的长度不为零则为真
#  =~ 前边变量包含后边变量则为真 , 本质上=~是正则匹配单层中括号不支持正则需要双层中括号 eg:  if [[  $var1 =~ $var2 ]]
# 数值的比较 例子
num1="ru1noob"
num2="runoob"
if [ $num1 = $num2 ]
then
    echo '两个字符串相等!'
else
    echo '两个字符串不相等!'
fi

#  4.逻辑运算（与/或/非）
#  与	-a或者&&	  eg:   if [ $var1 == '' -a $var2 != '' ]  或  if [ $var1 == '' ] && [ $var2 != '' ]
#  或	-o或者||	  eg:   if [ $var1 == '' -o $var2 != '' ]  或  if [ $var1 == '' ] || [ $var2 != '' ]
#  非	!	        eg:   if ! [ $var1 == '' ]               或  if [ ! $var1 == '' ]

# 参考 : https://www.cnblogs.com/lsdb/p/7735533.html