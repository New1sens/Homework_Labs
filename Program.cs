using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace lab_2
{
    public class HighInterfaceClient
    {
        public static void Main(string[] args)
        {
            Random random = new Random();
            int size = 100;
            pHighInterfaceArray array = new HighInterfaceArrayImpl(size);

            for (int i = 0; i < size; i++)
            {
                array.Insert(NextLong64(random, 50));
            }

            array.Display();

            ((HighInterfaceArrayImpl)array).Find();


            long searchValue = NextLong64(random, 50);
            if (array.Find(searchValue))
            {
                Console.WriteLine($"В массиве есть искомое значение, найдено. {searchValue}");
            }
            else
            {
                Console.WriteLine($"В массиве нет таких искомых значений. {searchValue}");
            }
            Console.WriteLine("Нажми пробел чтоб выйти");
            Console.ReadKey();
        }

        public static long NextLong64(Random random, long maxValue)
        {
            byte[] buffer = new byte[8];
            random.NextBytes(buffer);
            long result = BitConverter.ToInt64(buffer, 0);

            return Math.Abs(result % maxValue);
        }
    }
}
