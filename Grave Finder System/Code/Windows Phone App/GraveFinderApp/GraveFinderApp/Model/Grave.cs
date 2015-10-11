using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace GraveFinderApp
{
    public enum GenderType { Male, Female };

    public class Grave
    {
        public String Cemetery { get; set; }

        public String RowID { get; set; }

        public int GraveNumber { get; set; }

        public String Name { get; set; }

        public GenderType Gender { get; set; }

        public String Address { get; set; }

        public DateTime DOB { get; set; }

        public DateTime DOD { get; set; }

        public String InGrave { get; set; }

        public String Longtitude { get; set; }

        public String Latitude { get; set; }
    }
}