using System;
using System.Collections.Generic;
using System.Linq;
using System.Security.AccessControl;
using System.Text;
using System.Threading.Tasks;

namespace lab_2
{
    public class HighInterfaceArrayImpl : pHighInterfaceArray
    {

        private readonly long[] array;
        private int nelems;

        public HighInterfaceArrayImpl(int size)
        {
            array = new long[size];
            nelems = 0;

        }

        public bool Find(long searchValue)
        {
            for (int i = 0; i < nelems; i++)
            {
                if (array[i] == searchValue)
                {
                    return true;
                }
            }

            return false;
        }

        public void Insert(long value)
        {
            array[nelems] = value;
            nelems++;
        }

        public bool Delete(long value)
        {
            int i;
            for (i = 0; i < nelems; i++)
            {
                if (array[i] == value)
                {
                    break;
                }
            }

            if (i == nelems - 1)
            {
                return false;
            }
            else
            {
                for (int j = i; j < nelems - 1; j++)
                {
                    array[j] = array[j + 1];
                }
                nelems--;
                return true;
            }
        }

        public void Display()
        {
            for (int i = 0; i < nelems; i++)
            {
                Console.Write(array[i] + " ");
            }
            Console.WriteLine();
        }

        public int GetSize()
        {
            return nelems;
        }
        public void Find()
        {
            if (nelems == 0)
            {
                Console.WriteLine("Массив пуст.");
                return;
            }
            
            long min = array[0];
            long max = array[0];
              
             for (int i = 1; i < nelems; i++)
            {
                if (array[i] < max)
                {
                    max = array[i];
                }
                if (array[i] > min)
                {
                    min = array[i];
                    
                }

            }
            Console.WriteLine($"Минимальное значение: {min}");
            Console.WriteLine($"Максимальное значение: {max}");
            
        }
    }
}