import paramiko

def sftp_upload(local_path, remote_path, hostname, port, username, password):
    try:
        # Initialize the SSH client
        ssh = paramiko.SSHClient()
        # Automatically add the server's host key (not recommended for production)
        ssh.set_missing_host_key_policy(paramiko.AutoAddPolicy())

        # Connect to the remote server
        ssh.connect(hostname, port, username, password)

        # Start SFTP session
        sftp = ssh.open_sftp()

        # Upload the file
        sftp.put(local_path, remote_path)

        print(f"Successfully uploaded {local_path} to {remote_path}")

        # Close the SFTP session and SSH connection
        sftp.close()
        ssh.close()

    except paramiko.AuthenticationException:
        print("Authentication failed, please verify your credentials")
    except paramiko.SSHException as sshException:
        print(f"Unable to establish SSH connection: {sshException}")
    except paramiko.SFTPError as sftpException:
        print(f"Unable to establish SFTP session: {sftpException}")
    except FileNotFoundError as fnfError:
        print(f"Local file not found: {fnfError}")
    except Exception as e:
        print(f"An unexpected error occurred: {e}")

# Example usage
local_path = r'C:\Users\Moritz\Documents\GitHub\ProjectPlugin\target\ProjectPlugin-1.0.jar'
remote_path = r'/home/MinecraftSpigotServer_1.20.6/plugins/ProjectPlugin-1.0.jar'
hostname = '109.123.244.124'
port = 22
username = 'root'
password = 'tC8@E4@CML'

sftp_upload(local_path, remote_path, hostname, port, username, password)
