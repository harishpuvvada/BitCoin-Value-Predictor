#!/bin/bash

#SBATCH --nodes=1 
##SBATCH --cpus-per-task=10 
#SBATCH --time=23:59:59
##SBATCH --partition=gpu
#SBATCH --gres=gpu:1
#SBATCH --mem=3GB
#SBATCH --job-name=pythonTest
#SBATCH --mail-type=END
#SBATCH --mail-user=hp1047@nyu.edu
#SBATCH --output=slurm_%j.out

module purge
module load python3/intel/3.6.3
#module load numpy/intel/1.13.1
#module load cuda/8.0.44
#module load tensorflow/python2.7/1.0.1

cd /home/hp1047/py3.6.3

python Data_Extraction.py

