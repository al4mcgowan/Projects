using System;
using System.IO;
using System.Threading.Tasks;
using Windows.ApplicationModel;
using Windows.Storage;
using Windows.UI.Xaml;
using Windows.UI.Xaml.Controls;
using Windows.UI.Xaml.Navigation;

// The Blank Page item template is documented at http://go.microsoft.com/fwlink/?LinkID=390556

namespace GraveFinderApp
{
    public sealed partial class ResultsPage : Page
    {
        static string GeneratedHTML = "";
        public Double lat;
        public Double lon;

        public ResultsPage()
        {
            //intialization of the control is happening here
            this.InitializeComponent();
            //our WebView.
            LoadData();
        }
        private async void LoadData()
        {
            try
            {
                await loadJsonLocalnew("default.html", "");
            }
            catch (Exception ex)
            {

                throw;
            }
        }

        public async static Task loadJsonLocalnew(string url, object ClassName)
        {
            try
            {
                //*******************************************************
                //To pick up file from local folder in window store app
                //*******************************************************
                StorageFolder folder = await Package.Current.InstalledLocation.GetFolderAsync("htmlPage");
                StorageFile file = await folder.GetFileAsync(url);
                Stream stream = await file.OpenStreamForReadAsync();
                StreamReader reader = new StreamReader(stream);
                String html = reader.ReadToEnd();
                GeneratedHTML = html.ToString();
                //*******************************************************
                //*******************************************************
            }
            catch (Exception ex)
            {
            }
        }

        protected override void OnNavigatedTo(NavigationEventArgs e)
        {
            Grave g = e.Parameter as Grave;

            if (g == null)
            {
                DeceasedPerson.Text = "404 Not Found";
                Gender.Text = "";
                Cemetery.Text = "";
                LastAddress.Text = "";
                DOB.Text = "";
                DOD.Text = "";
                InGrave.Text = "";
            }
            else
            {
                // if g is not empty, print the details
                DeceasedPerson.Text = "Deceased Person: " + g.Name;
                Gender.Text = "Gender: " + g.Gender;
                Cemetery.Text = "Buried in: " + g.Cemetery + ", row " + g.RowID + ", number: " + g.GraveNumber;
                LastAddress.Text = "Last Address: " + "\n" + g.Address;
                DOB.Text = "Born on: " + g.DOB.Date.ToString("dd/MM/yyyy");
                DOD.Text = "Died on: " + g.DOD.Date.ToString("dd/MM/yyyy");
                InGrave.Text = "Also in the grave: " + g.InGrave;
                //Setting the HTML content in the WebView control.
                MapWebView.NavigateToString(GeneratedHTML);
                lat = Convert.ToDouble(g.Latitude);
                lon = Convert.ToDouble(g.Longtitude);
            }
        }

        private void BeginNewSearch(object sender, RoutedEventArgs e)
        {
            Frame.Navigate(typeof(Search));
        }

        private void HomeButton_Click(object sender, RoutedEventArgs e)
        {
            Frame.Navigate(typeof(Home));
        }
    }
}