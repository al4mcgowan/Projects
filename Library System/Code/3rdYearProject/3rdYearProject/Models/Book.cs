using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Data.Entity;
using System.ComponentModel.DataAnnotations;

namespace _3rdYearProject.Models
{
    public class Book
    {
        [Key]
        public int ID { get; set; }
        [Required(ErrorMessage = "ISBN is required using alphanumeric letters")]
        public string ISBN { get; set; }
        [Required(ErrorMessage = "Title is required")]
        [StringLength(100)]
        public string Title { get; set; }
        [Required(ErrorMessage = "Author is required")]
        [StringLength(250)]
        public string Author { get; set; }
        [Required(ErrorMessage = "Genre is required")]
        [StringLength(30)]
        public string Genre { get; set; }
        [Required(ErrorMessage = "Publisher is required")]
        [StringLength(350)]
        public string Publisher { get; set; }
        [Required(ErrorMessage = "Date published is required in the format of dd/MM/yyyy")]
        [DisplayFormat(ApplyFormatInEditMode = true, DataFormatString = "{0:dd/MM/yyyy}")]
        public DateTime PublishDate { get; set; }
        [Required(ErrorMessage = "Number of Copies can only be numeric values")]
        public int numCopies { get; set; }
        public string format { get; set; }
        public Boolean OnShelf { get; set; }
        public Boolean OnLoan { get; set; }
    }

    public class BookDBContext : DbContext
    {
        public DbSet<Book> Books { get; set; }
    }
}