namespace DatabaseGenerator.Data.Entities
{
	public class Account
	{
		public int Id { get; set; }
		public string FirstName { get; set; }
		public string LastName { get; set; }
		public string Username { get; set; }
		public string Role { get; set; }
		public string PhoneNumber { get; set; }
		public string Email { get; set; }
		public string IsAccountConfirmed { get; set; }
		public string Password { get; set; }
		public string Location { get; set; }
		public List<Product> Products { get; set; } = new List<Product>();
	}
}
