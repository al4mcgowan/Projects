using System;
using System.Collections.Generic;
using System.Diagnostics;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Net.Http.Headers;
using System.Web.Http;
using Windows.UI.Xaml;
using Windows.UI.Xaml.Controls;
using Windows.UI.Xaml.Navigation;

// The Blank Page item template is documented at http://go.microsoft.com/fwlink/?LinkId=391641

namespace GraveFinderApp
{
    public sealed partial class Search : Page
    {
        List<Grave> graves = new List<Grave>();

        public Search()
        {
            this.InitializeComponent();

            this.NavigationCacheMode = NavigationCacheMode.Required;
        }

        protected override void OnNavigatedTo(NavigationEventArgs e)
        {
            
        }

        private async void SearchButton_Click(object sender, RoutedEventArgs e)
        {
            DateTimeOffset selectedDobDate = this.DateOfBirth.Date;
            DateTimeOffset selectedDodDate = this.DateOfDeath.Date;

            // Or only the years have changed
            int dobyear = selectedDobDate.Year;
            int dodyear = selectedDodDate.Year;

            try
            {
                using (HttpClient client = new HttpClient())
                {
                    client.BaseAddress = new Uri("https://gmanager.azurewebsites.net/");
                    client.DefaultRequestHeaders.Accept.Add(new MediaTypeWithQualityHeaderValue("application/json"));

                    // GET ../api/Graves/
                    HttpResponseMessage response = await client.GetAsync("api/Graves");
                    if (response.IsSuccessStatusCode)
                    {
                        // read result 
                        var entries = await response.Content.ReadAsAsync<IEnumerable<Grave>>();
                        foreach (var grave in entries)
                        {
                            graves.Add(grave);
                        }
                        if (dobyear != DateTime.Now.Year || dodyear != DateTime.Now.Year)
                        {
                            var g = graves.FirstOrDefault(gr => gr.Name.ToUpper() == DeceasedPerson.Text.ToUpper() && gr.DOB.Year == dobyear || gr.DOD.Year == dodyear);
                            Frame.Navigate(typeof(ResultsPage), g);
                        }
                        else
                        {
                            var g = graves.FirstOrDefault(gr => gr.Name.ToUpper() == DeceasedPerson.Text.ToUpper() && DateTime.Compare(gr.DOB.Date, selectedDobDate.Date) == 0 || DateTime.Compare(gr.DOD.Date, selectedDodDate.Date) == 0);
                            Frame.Navigate(typeof(ResultsPage), g);
                        }
                    }
                    else
                    {
                        Debug.WriteLine(response.StatusCode);
                    }
                }
            }
            catch (Exception ex)
            {
                Debug.WriteLine(ex);
            }
        }

        // Called when the reset button is pressed
        private void ResetButton_Click(object sender, RoutedEventArgs e)
        {
            DeceasedPerson.Text = "";
            CemeteryNames.SelectedValue = CemeteryNames.PlaceholderText;
            DateOfBirth.Date = DateTime.Now;
            DateOfDeath.Date = DateTime.Now;
        }

        private void HelpButton_Click(object sender, RoutedEventArgs e)
        {
            Frame.Navigate(typeof(Help));
        }

        private void HomeButton_Click(object sender, RoutedEventArgs e)
        {
            Frame.Navigate(typeof(Home));
        }
    }
}
