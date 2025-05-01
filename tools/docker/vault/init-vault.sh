#!/bin/sh
echo "-------------------------Starting Vault in Dev Mode-------------------------"

# Start Vault in the background (& is used to run the command in the background)
vault server -dev &

# Wait for Vault to start
sleep 10

echo "-------------------------Running init script-------------------------"

# Enable kv
vault secrets enable -version=1 kv

# Enable database secrets engine
vault secrets enable database

echo "-------------------------Vault is ready-------------------------"

# Bring Vault to the foreground
# The wait command waits for the background process (vault server -dev) to finish.
# Since the Vault server runs indefinitely, the container will stay alive as long as the Vault server is running.
wait
