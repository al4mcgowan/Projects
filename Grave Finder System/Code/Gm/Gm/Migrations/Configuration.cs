namespace Gm.Migrations
{
    using System;
    using System.Data.Entity;
    using System.Data.Entity.Migrations;
    using System.Linq;
    using Gm.Models;
    using Microsoft.AspNet.Identity;
    using Microsoft.AspNet.Identity.EntityFramework;

    internal sealed class Configuration : DbMigrationsConfiguration<Gm.Models.ApplicationDbContext>
    {
        public Configuration()
        {
            AutomaticMigrationsEnabled = false;
        }

        bool AddUserAndRole(Gm.Models.ApplicationDbContext context)
        {
            IdentityResult ir;
            var rm = new RoleManager<IdentityRole>
                (new RoleStore<IdentityRole>(context));
            ir = rm.Create(new IdentityRole("canEdit"));
            var um = new UserManager<ApplicationUser>(
                new UserStore<ApplicationUser>(context));
            var user = new ApplicationUser()
            {
                UserName = "admin@glasnevintrust.ie",
            };
            ir = um.Create(user, "P_assw0rd1");
            if (ir.Succeeded == false)
                return ir.Succeeded;
            ir = um.AddToRole(user.Id, "canEdit");
            return ir.Succeeded;
        }

        protected override void Seed(Gm.Models.ApplicationDbContext context)
        {
            AddUserAndRole(context);

            context.Graves.AddOrUpdate(g => g.Name,
                new Grave
                {
                    Cemetery = "Glasnevin Cemetery",
                    RowID = "AA",
                    GraveNumber = 23,
                    Name = "Daniel O'Connell",
                    Gender = GenderType.Male,
                    Address = "Merrion Square",
                    DOB = new DateTime(1775, 08, 06),
                    DOD = new DateTime(1847, 05, 15),
                    InGrave = "None",
                    Longitude = -6.2771961,
                    Latitude = 53.3699538
                },
                new Grave
                {
                    Cemetery = "Glasnevin Cemetery",
                    RowID = "GD",
                    GraveNumber = 82,
                    Name = "Michael Collins",
                    Gender = GenderType.Male,
                    Address = "Portobello Barracks, Co. Dublin",
                    DOB = new DateTime(1890, 10, 16),
                    DOD = new DateTime(1922, 08, 22),
                    InGrave = "None",
                    Longitude = -6.276536,
                    Latitude = 53.369512
                },
                new Grave
                {
                    Cemetery = "Glasnevin Cemetery",
                    RowID = "ZE",
                    GraveNumber = 22,
                    Name = "Arthur Griffith",
                    Gender = GenderType.Male,
                    Address = "122, St. Laurence Road, Dublin",
                    DOB = new DateTime(1872, 03, 31),
                    DOD = new DateTime(1922, 08, 12),
                    InGrave = "Maud Sheehan (Wife), Nevin Griffith and Ita Griffith",
                    Longitude = -6.2775053,
                    Latitude = 53.371042
                },
                new Grave
                {
                    Cemetery = "Glasnevin Cemetery",
                    RowID = "ZE",
                    GraveNumber = 26,
                    Name = "Joseph Byrne",
                    Gender = GenderType.Male,
                    Address = "123 Malahide Road, Dublin",
                    DOB = new DateTime(1872, 03, 31),
                    DOD = new DateTime(1920, 06, 12),
                    InGrave = "Mary Byrne (Sister)",
                    Longitude = -6.2785053,
                    Latitude = 53.371142
                },
                new Grave
                {
                    Cemetery = "Newlands Cross",
                    RowID = "A",
                    GraveNumber = 36,
                    Name = "Tina McIntyre",
                    Gender = GenderType.Female,
                    Address = "14 Bawnogue, Clondalkin, Dublin 22",
                    DOB = new DateTime(1992, 02, 18),
                    DOD = new DateTime(2014, 06, 23),
                    InGrave = "Joe McIntyre (Father)",
                    Longitude = -6.377879,
                    Latitude = 53.311166
                },
                new Grave
                {
                    Cemetery = "Newlands Cross",
                    RowID = "F",
                    GraveNumber = 29,
                    Name = "Robert McGuinness",
                    Gender = GenderType.Male,
                    Address = "40 Michael's Estate, Inchicore, Dublin 8",
                    DOB = new DateTime(1990, 04, 06),
                    DOD = new DateTime(2012, 09, 12),
                    InGrave = "None",
                    Longitude = -6.378999,
                    Latitude = 53.311091
                },
                new Grave
                {
                    Cemetery = "Deansgrange",
                    RowID = "Patrick",
                    GraveNumber = 44,
                    Name = "Ann Stevens",
                    Gender = GenderType.Female,
                    Address = "65 Sundale Grove, Tallaght, Dublin 24",
                    DOB = new DateTime(1968, 02, 17),
                    DOD = new DateTime(2010, 05, 14),
                    InGrave = "Mark Stevens (Husband)",
                    Longitude = -6.167571,
                    Latitude = 53.283016
                },
                new Grave
                {
                    Cemetery = "Deansgrange",
                    RowID = "Michael",
                    GraveNumber = 14,
                    Name = "Ben Grogan",
                    Gender = GenderType.Male,
                    Address = "44 Sundale Avenue, Malahide, Dublin",
                    DOB = new DateTime(1954, 11, 10),
                    DOD = new DateTime(2014, 10, 21),
                    InGrave = "Rita Grogan (Wife)",
                    Longitude = -6.169811,
                    Latitude = 53.279020
                });
            //  This method will be called after migrating to the latest version.

            //  You can use the DbSet<T>.AddOrUpdate() helper extension method 
            //  to avoid creating duplicate seed data. E.g.
            //
            //    context.People.AddOrUpdate(
            //      p => p.FullName,
            //      new Person { FullName = "Andrew Peters" },
            //      new Person { FullName = "Brice Lambson" },
            //      new Person { FullName = "Rowan Miller" }
            //    );
            //
        }
    }
}
