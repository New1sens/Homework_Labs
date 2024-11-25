using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace lab_2
{
          public interface pHighInterfaceArray
        {
            bool Find(long searchValue);
            void Insert(long value);
            bool Delete(long value);
            void Display();
            int GetSize();
            void Find();
        }
}
