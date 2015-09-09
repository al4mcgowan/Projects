using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.Linq;
using System.Web;

namespace Gm.Models
{
    public enum GenderType { Male, Female };

    public class Grave
    {
        [Key]
        public int GraveID { get; set; }

        [Required(ErrorMessage = "Must enter the name of the graveyard")]
        public String Cemetery { get; set; }

        [Required(ErrorMessage = "Must enter either the letters/number of the row")]
        [Display(Name = "Row ID")]
        public String RowID { get; set; }

        [Required(ErrorMessage = "Must enter the number of the grave")]
        [Display(Name = "Grave Number")]
        public int GraveNumber { get; set; }

        [Required(ErrorMessage = "Must enter the name of the deceased person")]
        [Display(Name = "Deceased Person")]
        public String Name { get; set; }

        [Required(ErrorMessage = "Must select the gender of the deceased person")]
        public GenderType Gender { get; set; }

        [Required(ErrorMessage = "Must enter the last address of the deceased person")]
        [Display(Name = "Last Address")]
        public String Address { get; set; }

        [Required(ErrorMessage = "Must enter the date of birth of the deceased person")]
        [Display(Name = "Date of Birth")]
        [DisplayFormat(ApplyFormatInEditMode = true, DataFormatString = "{0:dd/MM/yyyy}")]
        [DataType(DataType.Date)]
        public DateTime DOB { get; set; }

        [Required(ErrorMessage = "Must enter the date the deceased person died")]
        [Display(Name = "Date of Death")]
        [DisplayFormat(ApplyFormatInEditMode = true, DataFormatString = "{0:dd/MM/yyyy}")]
        [DataType(DataType.Date)]
        public DateTime DOD { get; set; }

        [Display(Name = "Also in grave")]
        public String InGrave { get; set; }

        [Required(ErrorMessage = "Must enter the longitude part of the geo-location")]
        public Double Longitude { get; set; }

        [Required(ErrorMessage = "Must enter the latitude part of the geo-location")]
        public Double Latitude { get; set; }
    }
}